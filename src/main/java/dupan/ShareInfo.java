package dupan;

public class ShareInfo {
    String link;
    String shortUrl;
    String password;
    long ctime;
    int expiredType;

    public ShareInfo(String link, String shortUrl, String password, long ctime, int expiredType) {
        this.link = link;
        this.shortUrl = shortUrl;
        this.password = password;
        this.ctime = ctime;
        this.expiredType = expiredType;
    }

    @Override
    public String toString() {
        return "ShareInfo{" +
                "link='" + link + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                ", password='" + password + '\'' +
                ", ctime=" + ctime +
                ", expiredType=" + expiredType +
                '}';
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public int getExpiredType() {
        return expiredType;
    }

    public void setExpiredType(int expiredType) {
        this.expiredType = expiredType;
    }
}
