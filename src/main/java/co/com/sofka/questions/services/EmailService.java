package co.com.sofka.questions.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.CoreSubscriber;
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
                                String body
    ) {
        MimeMessage mail = mailSender.createMimeMessage();
//            mail.setContent("<h1>Hola Mundo</h1>", "text/html; charset=utf-8");
//            mail.setFrom("marloncamilasofka@gmail.com");
//            mail.setSubject(subject);
//            mail.setRecipients(Message.RecipientType.TO,toEmail);

//            SimpleMailMessage message = new SimpleMailMessage();
//                message.setFrom("marloncamilasofka@gmail.com");
//                message.setTo(toEmail);
//              //  message.setText();
//                message.setText("<h1>Hola Mundo</h1>");
//                message.setSubject(subject);
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.host", "smtp.gmail.com");
        props.put("mail.port", "587");
        props.put("mail.username","marloncamilasofka@gmail.com");
        props.put("mail.password","zbeocvahurvudhgo");
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

            // Send the actual HTML message, as big as you like
            message.setContent(
                    "<h1>This is actual message embedded in HTML tags</h1>",
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
//        MimeMessage message = sender.createMimeMessage(session);
//
//// use the true flag to indicate you need a multipart message
//        Mono<String> m;
//        try {
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setTo(toEmail);
//            helper.setSubject(subject);
//            helper.setText("<html><body><img src='cid:identifier1234'></body></html>", true);
//            sender.send(helper.getMimeMessage());
//            m = Mono.just("marloncamilasofka@gmail.com");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//            m = Mono.just(e.getMessage());
//        }
//        return m;
// use the true flag to indicate the text included is HTML


// let's include the infamous windows Sample file (this time copied to c:/)


//
//                try {
//              //      mailSender.send(mail);
//                //mailSender.send(message);
//                m = Mono.just("marloncamilasofka@gmail.com");
//            } catch (MailException ex) {
//                //log it and go on
//                System.err.println(ex.getMessage());
//                    m = Mono.just(ex.getMessage());
//            }
    }

    }
       // System.out.println();
