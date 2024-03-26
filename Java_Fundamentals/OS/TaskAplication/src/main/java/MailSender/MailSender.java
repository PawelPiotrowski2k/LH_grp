package MailSender;

import Config.ConfigManager;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender {
    private final String username;
    private final String password;
    private final Properties props;

    public MailSender(Session session, Properties confProp) {
        ConfigManager configManager = new ConfigManager();
        username = configManager.getMailLogin();
        password = configManager.getMailPassword();
        this.props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }
    public void SendMessage() throws MailSenderException {

            try {
                Message message = new MimeMessage (getSesion());
                message.setFrom(new InternetAddress(username));
                message.setRecipients(
                        Message.RecipientType.TO,
                        InternetAddress.parse("wikikarpik08@wp.pl")
                );
                message.setSubject("Testowa wiadomość");
                message.setText("test");

                Transport.send(message);

            } catch (MessagingException e){
                throw new MailSenderException("Messaging exception " + e);
            }
}
    private Session getSesion(){
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        return session;
    }
}
