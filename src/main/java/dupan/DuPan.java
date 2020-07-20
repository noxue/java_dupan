package dupan;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class QrCode {

    String imgurl;
    String uuid;
    String sign;

    public QrCode() {
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public QrCode(String imgurl, String uuid, String sign) {
        this.imgurl = imgurl;
        this.uuid = uuid;
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "dupan.QrCode{" +
                "imgurl='" + imgurl + '\'' +
                ", uuid='" + uuid + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}

/**
 * 网盘扫码登陆状态
 */
enum LoginState {
    FAIL, WAITING, SCAN, SUCCESS
}

public class DuPan {
    public static void main(String[] args) throws IOException {

        DuPan pan = new DuPan();
        boolean b = pan.getQrCode();
        System.out.println(pan.qrCode);

        for (int i = 0; i < 5; i++) {
            if (pan.CheckLogin() == LoginState.SUCCESS) {
                break;
            }
        }
        List<PanFile> files = pan.list("/", 1, 100, false);
        System.out.println(files);

        ShareInfo info = pan.share("1072766333198351", "dot2", 1);
    }

    CloseableHttpClient httpClient;
    CookieStore cookieStore;
    QrCode qrCode = null;
    String bduss; // 扫码确定后，登陆获取cookie需要用到的参数
    String token;  // 也是不知道啥用，留着备用吧
    String bdstoken; // 没深究什么作用，有时候请求要带上
    String username; // 百度账号名

    public DuPan() {
        cookieStore = new BasicCookieStore();
        httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
    }

    /***
     * 第一步：获取二维码图片信息
     * @return 返回 true 表示成功
     * @throws IOException
     */
    public boolean getQrCode() {
        String uuid = UUID.randomUUID().toString().toUpperCase();
        String url = "https://passport.baidu.com/v2/api/getqrcode?lp=pc&qrloginfrom=pc&gid=" + uuid + "&apiver=v3";
        HttpGet get = new HttpGet(url);
        try {
            HttpResponse res = httpClient.execute(get);
            String content = EntityUtils.toString(res.getEntity(), "utf-8");
            JSONObject json = JSONObject.parseObject(content);
            qrCode = new QrCode(json.getString("imgurl"), uuid, json.getString("sign"));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public LoginState CheckLogin() {
        String url = "https://passport.baidu.com/channel/unicast?channel_id=" +
                qrCode.getSign() +
                "&tpl=netdisk&gid=" +
                qrCode.getUuid() +
                "&callback=&apiver=v3";

        HttpGet get = new HttpGet(url);
        try {
            HttpResponse res = httpClient.execute(get);
            String content = EntityUtils.toString(res.getEntity(), "utf-8");
            content = content.substring(1, content.length() - 2);
            JSONObject json = JSONObject.parseObject(content);
            int errno = json.getInteger("errno");
            if (errno != 0) {
                return LoginState.WAITING;
            }
            json = JSONObject.parseObject(json.getString("channel_v"));
            int status = json.getInteger("status");
            if (status != 0) {
                System.out.println("二维码已扫描，点击确认即可");
                return LoginState.SCAN;
            }
            bduss = json.getString("v");

            // 请求登陆链接，获取cookie，并获取用户信息
            if (Login()) {
                System.out.println("登陆成功");
                return LoginState.SUCCESS;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return LoginState.FAIL;
    }

    boolean Login() {
        String url = "https://passport.baidu.com/v3/login/main/qrbdusslogin?v=1595252895074&bduss=" + bduss + "&u=https%253A%252F%252Fpan.baidu.com%252Fdisk%252Fhome&loginVersion=v4&qrcode=1&tpl=netdisk&apiver=v3&tt=1595252895074&traceid=&time=1595252895&alg=v3&callback=";

        HttpGet get = new HttpGet(url);
        try {
            HttpResponse res = httpClient.execute(get);
            String content = EntityUtils.toString(res.getEntity(), "utf-8");
            content = content.replaceAll("\\\\&", "&");
            System.out.println("==============================");
            System.out.println(content);
            JSONObject json = JSONObject.parseObject(content);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // 获取用户信息
        try {
            get = new HttpGet("https://pan.baidu.com/disk/home");
            HttpResponse res = httpClient.execute(get);
            String content = EntityUtils.toString(res.getEntity(), "utf-8");
            Matcher ma = Pattern.compile("(\\{\"loginstate\".+?})\\)").matcher(content);

            if (!ma.find()) {
                return false;
            }
            content = content.substring(ma.start(), ma.end() - 1);
            JSONObject json = JSONObject.parseObject(content);
            token = json.getString("token");
            bdstoken = json.getString("bdstoken");
            username = json.getString("username");

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }


    /***
     * 列出网盘指定目录的文件
     * @param dir 要列出的路径
     * @param page 列出第几页
     * @param page_size 每页文件数
     * @param showempty 是否显示空文件
     * @return 返回文件列表
     */
    public List<PanFile> list(String dir, int page, int page_size, boolean showempty) {
        String url = "https://pan.baidu.com/api/list?order=size&desc=0&showempty="
                + (showempty ? "1" : "0") + "&web=1&page="
                + page + "&num="
                + page_size + "&dir=" + dir
                + "&t=0.36896752387281184&channel=chunlei&web=1&app_id=250528&bdstoken="
                + bdstoken + "&logid=MTU5NTI1MjMxODE3MDAuOTg5NjcwNTYzNjYxNTc4MQ==&clienttype=0&startLogTime=1595252318170";
        HttpGet get = new HttpGet(url);
        List<PanFile> files = new ArrayList<PanFile>();
        try {
            HttpResponse res = httpClient.execute(get);
            String content = EntityUtils.toString(res.getEntity(), "utf-8");
            System.out.println(content);
            JSONObject json = JSONObject.parseObject(content);
            if (json.getInteger("errno") != 0) {
                return null;
            }
            files = JSON.parseObject(json.getString("list"), new TypeReference<ArrayList<PanFile>>() {
            });
            return files;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }

    /**
     * 分享文件
     *
     * @param fid_list   要分享的文件 fs_id 列表
     * @param password   要分享的文件密码，只能是 4 个字符
     * @param expireType 过期时间，只能是 1， 7， 30 天
     * @return 返回分享的结果
     * @throws IOException
     */
    public ShareInfo share(List<String> fid_list, String password, int expireType) throws IOException {
        String url = "https://pan.baidu.com/share/set?channel=chunlei&clienttype=0&web=1" +
                "&channel=chunlei&web=1&app_id=250528&bdstoken="
                + bdstoken
                + "&logid=MTU5NTI2MTkyNDI0MzAuNDY3OTg0MjU0MDkyOTg3Mw==&clienttype=0";
        HttpPost post = new HttpPost(url);
        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("schannel", "4"));
        list.add(new BasicNameValuePair("channel_list", "[]"));
        list.add(new BasicNameValuePair("period", expireType + ""));
        list.add(new BasicNameValuePair("pwd", password));
        list.add(new BasicNameValuePair("fid_list", fid_list.toString()));
        UrlEncodedFormEntity forms = new UrlEncodedFormEntity(list);

        post.setEntity(forms);
        HttpResponse res = httpClient.execute(post);
        String content = EntityUtils.toString(res.getEntity(), "utf-8");
        JSONObject json = JSONObject.parseObject(content);
        if (json.getInteger("errno") != 0) {
            return null;
        }

        ShareInfo info = JSON.parseObject(content, new TypeReference<>() {
        });
        info.setPassword(password);
        return info;
    }


    public ShareInfo share(String fid, String password, int expireType) throws IOException {
        List<String> fid_list = new ArrayList<>();
        fid_list.add(fid);
        return share(fid_list, password, expireType);
    }

    public ShareInfo share(String fid, String password) throws IOException {
        return share(fid, password, 7);
    }

}
