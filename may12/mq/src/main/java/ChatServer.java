import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Scanner;

/**
 * Created by eku on 12.05.17.
 */
public class ChatServer {
    public static Connection createConnection() throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory =
                new ActiveMQConnectionFactory("tcp://localhost:61616");

        return activeMQConnectionFactory.createConnection();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String message = null;

        System.out.println("Enter you name:");
        String name = scanner.nextLine();

        System.out.println("Enter you channel:");
        String chatChannel = scanner.nextLine();

        new Thread(() -> {
            try {
                Connection connection = createConnection();
                connection.start();
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                Destination destination = session.createQueue(chatChannel);

                MessageConsumer messageConsumer = session.createConsumer(destination);

                Message chatMessage = messageConsumer.receive(1000);
                while (!("stop".equals(chatMessage))) {
                    if (chatMessage != null) {
                        String mes = ((TextMessage) chatMessage).getText();
                        if (!mes.split(": ")[0].equals(name)) {
                            System.out.println(mes);
                        }
                    }
                    chatMessage = messageConsumer.receive(1000);
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            Connection connection = createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(chatChannel);
            MessageProducer messageProducer = session.createProducer(destination);
            messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);

            while (!(message = scanner.nextLine()).equals("stop")) {
                TextMessage textMessage = session.createTextMessage(name + ":" + message);
                messageProducer.send(textMessage);
            }

            messageProducer.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
