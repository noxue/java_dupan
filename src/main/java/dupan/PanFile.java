package dupan;

public class PanFile {
    String server_filename;
    int privacy;
    int category;
    int unlist;
    long fs_id;
    int dir_empty;
    int server_atime;
    long server_ctime;
    long local_mtime;
    int size;
    int isdir;
    int share;
    String path;
    long local_ctime;
    long server_mtime;
    int empty;
    int oper_id;

    public PanFile() {
    }

    public PanFile(String server_filename, int privacy, int category, int unlist, long fs_id, int dir_empty, int server_atime, long server_ctime, long local_mtime, int size, int isdir, int share, String path, long local_ctime, long server_mtime, int empty, int oper_id) {
        this.server_filename = server_filename;
        this.privacy = privacy;
        this.category = category;
        this.unlist = unlist;
        this.fs_id = fs_id;
        this.dir_empty = dir_empty;
        this.server_atime = server_atime;
        this.server_ctime = server_ctime;
        this.local_mtime = local_mtime;
        this.size = size;
        this.isdir = isdir;
        this.share = share;
        this.path = path;
        this.local_ctime = local_ctime;
        this.server_mtime = server_mtime;
        this.empty = empty;
        this.oper_id = oper_id;
    }

    public String getServer_filename() {
        return server_filename;
    }

    public void setServer_filename(String server_filename) {
        this.server_filename = server_filename;
    }

    public int getPrivacy() {
        return privacy;
    }

    public void setPrivacy(int privacy) {
        this.privacy = privacy;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getUnlist() {
        return unlist;
    }

    public void setUnlist(int unlist) {
        this.unlist = unlist;
    }

    public long getFs_id() {
        return fs_id;
    }

    public void setFs_id(long fs_id) {
        this.fs_id = fs_id;
    }

    public int getDir_empty() {
        return dir_empty;
    }

    public void setDir_empty(int dir_empty) {
        this.dir_empty = dir_empty;
    }

    public int getServer_atime() {
        return server_atime;
    }

    public void setServer_atime(int server_atime) {
        this.server_atime = server_atime;
    }

    public long getServer_ctime() {
        return server_ctime;
    }

    public void setServer_ctime(long server_ctime) {
        this.server_ctime = server_ctime;
    }

    public long getLocal_mtime() {
        return local_mtime;
    }

    public void setLocal_mtime(long local_mtime) {
        this.local_mtime = local_mtime;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getIsdir() {
        return isdir;
    }

    public void setIsdir(int isdir) {
        this.isdir = isdir;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getLocal_ctime() {
        return local_ctime;
    }

    public void setLocal_ctime(long local_ctime) {
        this.local_ctime = local_ctime;
    }

    public long getServer_mtime() {
        return server_mtime;
    }

    public void setServer_mtime(long server_mtime) {
        this.server_mtime = server_mtime;
    }

    public int getEmpty() {
        return empty;
    }

    public void setEmpty(int empty) {
        this.empty = empty;
    }

    public int getOper_id() {
        return oper_id;
    }

    public void setOper_id(int oper_id) {
        this.oper_id = oper_id;
    }


    @Override
    public String toString() {
        return "PanFile{" +
                "server_filename='" + server_filename + '\'' +
                ", privacy=" + privacy +
                ", category=" + category +
                ", unlist=" + unlist +
                ", fs_id=" + fs_id +
                ", dir_empty=" + dir_empty +
                ", server_atime=" + server_atime +
                ", server_ctime=" + server_ctime +
                ", local_mtime=" + local_mtime +
                ", size=" + size +
                ", isdir=" + isdir +
                ", share=" + share +
                ", path='" + path + '\'' +
                ", local_ctime=" + local_ctime +
                ", server_mtime=" + server_mtime +
                ", empty=" + empty +
                ", oper_id=" + oper_id +
                '}';
    }
}
