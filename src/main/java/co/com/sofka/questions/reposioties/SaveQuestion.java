package co.com.sofka.questions.reposioties;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.model.QuestionDTO;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

public interface SaveQuestion  {
    Mono<String> apply(@Valid QuestionDTO questionDTO);
}
