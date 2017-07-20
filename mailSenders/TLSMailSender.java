//класс отсылает письмо на gmail по протоколу TLS
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class TLSMailSender {
    private String username = "***";
    private String password = "***";
    private Properties props;


    public void send(String subject, String text){
        props = new Properties();                     //настраиваем проперти 
        props.put("mail.smtp.auth", "true");          //запрос аутентификации
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");   //какая-то фича чтобы гугл не нервничал, 
                                                      //но она не срабатывает и все-равно приходится ручками отключать 
        props.put("mail.smtp.starttls.enable", "true"); //активация TLS протокола
        props.put("mail.smtp.host", "smtp.gmail.com");  //хост
        props.put("mail.smtp.port", "587");             //порт

        Session session = Session.getInstance(props, new Authenticator() {        //аутентификация
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);         //создание сообщения
            message.setFrom(new InternetAddress(username));     //автор
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("******"));     //получатель/-ли
            message.setSubject(subject);          //тема письма
            message.setText(text);                 //содержимое

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        TLSMailSender tlsMailSender = new TLSMailSender();
        tlsMailSender.send("test letter", "asta la vista, baby \n I`ll be back");
    }
}
