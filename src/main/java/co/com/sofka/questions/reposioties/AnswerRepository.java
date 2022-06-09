package co.com.sofka.questions.reposioties;

import co.com.sofka.questions.collections.Answer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface AnswerRepository extends ReactiveMongoRepository<Answer, String> {
    Flux<Answer> findAllByQuestionId(String id);

    Mono<Void> deleteByQuestionId(String questionId);
}
