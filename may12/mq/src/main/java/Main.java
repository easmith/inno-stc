/**
 * Created by eku on 12.05.17.
 */
public class Main {
    public static void main(String[] args) {
        new Thread(() -> {
            MyMessageConsumer consumer = new MyMessageConsumer();

            consumer.receiveMessage();
        }).start();
        new Thread(() ->{
            MyMessageProducer producer = new MyMessageProducer();

            producer.sendMessage("Hello");
        }).start();

    }
}
