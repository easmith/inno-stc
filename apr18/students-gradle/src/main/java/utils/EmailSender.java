package utils;

import controllers.LoginController;
import org.apache.log4j.Logger;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * Created by eku on 20.04.17.
 */
public class EmailSender {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    public static void send(String toEmail, String text) {
        LOGGER.info("Send emailTo: " + toEmail + " Text: " + text);

//        final String username = "easmithrus@gmail.com";
//        final String password = "OM4zrnzF";
//
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//
//        Session session = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(username, password);
//                    }
//                });
//
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(username));
//            message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse(toEmail));
//            message.setSubject("From app");
//            message.setText(text);
//
////            MimeBodyPart messageBodyPart = new MimeBodyPart();
////            Multipart multipart = new MimeMultipart();
////
////            messageBodyPart = new MimeBodyPart();
////            DataSource source = new FileDataSource(fileName);
////            messageBodyPart.setDataHandler(new DataHandler(source));
////            messageBodyPart.setFileName("log.log");
////            multipart.addBodyPart(messageBodyPart);
////
////            message.setContent(multipart);
//
//            Transport.send(message);
//
//            System.out.println("Done");
//
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
    }
}
