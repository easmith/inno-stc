/**
 * Created by eku on 12.05.17.
 */
public class Main2 {

    public static void main(String[] args) {
        MyMessageProducer messageProducer = new MyMessageProducer();
        messageProducer.sendMessage("Hello!");
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
