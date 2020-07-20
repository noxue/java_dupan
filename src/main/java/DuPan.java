import com.alibaba.fastjson.JSONObject;
import jdk.jfr.DataAmount;
import netscape.javascript.JSObject;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
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
        return "QrCode{" +
                "imgurl='" + imgurl + '\'' +
                ", uuid='" + uuid + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}

/**
 * 网盘扫码登陆状态
 */
enum DuPanLoginState {
    FAIL, WAITING, SCAN, SUCCESS
}

public class DuPan {
    public static void main(String[] args) throws IOException {
        DuPan pan = new DuPan();
        boolean b = pan.getQrCode();
        System.out.println(pan.qrCode);

        for (int i = 0; i < 5; i++) {
            if (pan.CheckLogin() == DuPanLoginState.SUCCESS) {
                break;
            }
        }
        pan.list();
    }

    CloseableHttpClient httpClient = null;
    CookieStore cookieStore = new BasicCookieStore();
    QrCode qrCode = null;
    String bduss; // 扫码确定后，登陆获取cookie需要用到的参数
    String token;
    String bdstoken;
    String username;

    public DuPan() {

        httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
    }

    /***
     * 第一步：获取二维码图片信息
     * @return
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

    public DuPanLoginState CheckLogin() {
        String url = "https://passport.baidu.com/channel/unicast?channel_id=" +
                qrCode.getSign() +
                "&tpl=netdisk&gid=" +
                qrCode.getUuid() +
                "&callback=&apiver=v3";

        HttpGet get = new HttpGet(url);
        try {
            HttpResponse res = httpClient.execute(get);

            System.out.println("=======================================");
            for (Cookie h : cookieStore.getCookies()) {
                System.out.println(h.getName() + ": " + h.getValue());
            }
            System.out.println("=======================================");

            String content = EntityUtils.toString(res.getEntity(), "utf-8");
            content = content.substring(1, content.length() - 2);
            System.out.println(content);
            JSONObject json = JSONObject.parseObject(content);
            int errno = json.getInteger("errno");
            if (errno != 0) {
                return DuPanLoginState.WAITING;
            }
            json = JSONObject.parseObject(json.getString("channel_v"));
            int status = json.getInteger("status");
            if (status != 0) {
                System.out.println("已扫描");
                return DuPanLoginState.SCAN;
            }
            bduss = json.getString("v");

            // 请求登陆链接，获取cookie，并获取用户信息
            if (Login()) {
                return DuPanLoginState.SUCCESS;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return DuPanLoginState.FAIL;
    }

    boolean Login() {
        String url = "https://passport.baidu.com/v3/login/main/qrbdusslogin?v=1595252895074&bduss=" + bduss + "&u=https%253A%252F%252Fpan.baidu.com%252Fdisk%252Fhome&loginVersion=v4&qrcode=1&tpl=netdisk&apiver=v3&tt=1595252895074&traceid=&time=1595252895&alg=v3&callback=";

        HttpGet get = new HttpGet(url);
        try {
            HttpResponse res = httpClient.execute(get);
            String content = EntityUtils.toString(res.getEntity(), "utf-8");
            content = content.replaceAll("\\\\&", "&");
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
//            System.out.println(content);
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

    public void list(){
        String url = "https://pan.baidu.com/api/list?order=size&desc=0&showempty=0&web=1&page=1&num=100&dir=%2F&t=0.36896752387281184&channel=chunlei&web=1&app_id=250528&bdstoken="+bdstoken+"&logid=MTU5NTI1MjMxODE3MDAuOTg5NjcwNTYzNjYxNTc4MQ==&clienttype=0&startLogTime=1595252318170";
        HttpGet get = new HttpGet(url);
        try {
            HttpResponse res = httpClient.execute(get);
            String content = EntityUtils.toString(res.getEntity(), "utf-8");
//            content = content.replaceAll("\\\\&", "&");
            System.out.println(content);
            JSONObject json = JSONObject.parseObject(content);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
