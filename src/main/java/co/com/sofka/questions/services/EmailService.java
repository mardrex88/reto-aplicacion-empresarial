package co.com.sofka.questions.services;

import co.com.sofka.questions.model.EmailBodyDTO;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Mono<String> sendEmail(String toEmail,
                                String subject,
                                String bodyQuestion,
                                  String idQuestion
    ) {
        MimeMessage mail = mailSender.createMimeMessage();

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
                        return new PasswordAuthentication("marloncamilasofka@gmail.com", "zbeocvahurvudhgo");
                    }
                });

        sender.setJavaMailProperties(props);
        Mono<String> m;
        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress("marloncamilasofka@gmail.com"));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));

            // Set Subject: header field
            message.setSubject(subject);

            //Cuerpo del correo
            String content = EmailBodyDTO.getBodyMailQuestion(bodyQuestion,idQuestion);

            // Send the actual HTML message, as big as you like
            message.setContent(
                    content,
                    "text/html");

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");
            m = Mono.just("marloncamilasofka@gmail.com");
        } catch (MessagingException e) {
            e.printStackTrace();

            m = Mono.just(e.getMessage());
            throw new RuntimeException(e);
        }
        return m;
    }

    }

