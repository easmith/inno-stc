/**
 * Created by eku on 25.04.17.
 */
public class Main {
    private static Downloader downloader;
    private static Uploader uploader;
    public static void main(String[] args) {
        downloader = new FtpDownloader();
        uploader = new FtpUploader();

        String content = downloader.download("");
        uploader.upload(content);
    }
}
