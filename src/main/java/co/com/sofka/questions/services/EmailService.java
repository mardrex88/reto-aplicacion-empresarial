package co.com.sofka.questions.services;

import co.com.sofka.questions.model.EmailBodyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    /*
    *Usamos la Anotacion Value para traer el usuario y la contraseña
    * */
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;

    private Mono<String> result;

    public Mono<String> sendEmail(String toEmail,String subject,String bodyQuestion,String idQuestion)
    {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.host", "smtp.gmail.com");
        props.put("mail.port", "587");
        props.put("mail.smtp.ssl.trust","smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        sender.setJavaMailProperties(props);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("marloncamilasofka@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(toEmail));
            message.setSubject(subject);

            //Con EmailBodyDTO.getBodyMailQuestion obtenemos un string con el cuerpo del email en Html
            String content = EmailBodyDTO.getBodyMailQuestion(bodyQuestion,idQuestion);

            message.setContent(content,"text/html");

            //Enviamos el mensaje
            Transport.send(message);

            System.out.println("Sent message successfully....");
            result = Mono.just("marloncamilasofka@gmail.com");
        } catch (MessagingException e) {
            e.printStackTrace();
            result = Mono.just(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

}

