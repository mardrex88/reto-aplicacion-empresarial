package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.EmailBodyDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class SendEmailUseCase  {

    @Autowired
    EmailService emailService;

    public void send(EmailBodyDTO emailBodyDTO) {
        Objects.requireNonNull(emailBodyDTO.getToEmail(), "email to reuired");
        Objects.requireNonNull(emailBodyDTO.getSubject(), "subject required");
        Objects.requireNonNull(emailBodyDTO.getBodyQuestion(), "BodyQuestion required");
        Objects.requireNonNull(emailBodyDTO.getIdQuestion(), "IdQuestion required");
        emailService.sendEmail(emailBodyDTO.getToEmail(),emailBodyDTO.getSubject(),emailBodyDTO.getBodyQuestion(),emailBodyDTO.getIdQuestion());
    }
}
