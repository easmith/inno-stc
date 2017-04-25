/**
 * Created by eku on 25.04.17.
 */
public class HttpUploader implements Uploader {
    @Override
    public Boolean upload(String content) {
        System.out.println(content + " downloaded by http");
        return true;
    }
}
