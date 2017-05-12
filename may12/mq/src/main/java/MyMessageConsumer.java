
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by eku on 12.05.17.
 */
public class MyMessageConsumer {

    public Connection createConnection() throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory =
                new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");

        return activeMQConnectionFactory.createConnection();
    }

    public void receiveMessage() {
        try {
            Connection connection = createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("MyQueue");

            MessageConsumer messageConsumer = session.createConsumer(destination);

            Message message = messageConsumer.receive(10000);

            System.out.println(((TextMessage) message).getText());
            messageConsumer.close();
            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
