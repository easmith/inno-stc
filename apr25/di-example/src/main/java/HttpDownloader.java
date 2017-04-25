/**
 * Created by eku on 25.04.17.
 */
public class HttpDownloader implements Downloader {
    @Override
    public String download(String path) {
        return "I'm string from http";
    }
}
