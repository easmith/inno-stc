/**
 * Created by eku on 25.04.17.
 */
public class FtpUploader implements Uploader {
    @Override
    public Boolean upload(String content) {
        System.out.println(content + " downloaded by ftp");
        return true;
    }
}
