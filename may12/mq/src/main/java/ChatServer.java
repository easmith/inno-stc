import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.jms.*;
import java.util.Scanner;


/**
 * Created by eku on 12.05.17.
 */
public class ChatServer {

    private static final Logger LOGGER = Logger.getLogger(ChatServer.class);

    static {
        PropertyConfigurator.configure("/home/eku/proj/stc/may12/mq/src/main/java/log4j.properties");
    }

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

                String chatMessage = null;
                while (!("stop".equals(chatMessage))) {
                    Message mqMessage = messageConsumer.receive(1000);
                    if (mqMessage != null) {
                        chatMessage = ((TextMessage) mqMessage).getText();
                        if (!name.equals(chatMessage.split(": ")[0])) {
                            System.out.println(chatMessage);
                        } else {
                            LOGGER.info("Получил сообщение от себя");
                        }
                    }
                }
                System.out.println("Good bye");
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
                TextMessage textMessage = session.createTextMessage(name + ": " + message);
                messageProducer.send(textMessage);
            }

            messageProducer.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
