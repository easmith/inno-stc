import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by eku on 25.04.17.
 */
public class Main {
    private static Downloader downloader;
    private static Uploader uploader;
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        downloader = (Downloader) context.getBean("downloader");
        uploader = (Uploader) context.getBean("uploader");

        String content = downloader.download("");
        uploader.upload(content);
    }
}
