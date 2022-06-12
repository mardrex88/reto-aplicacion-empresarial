package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class UpdateUseCaseTest {

    MapperUtils mapperUtils;
    QuestionRepository questionRepository;
    UpdateUseCase updateUseCase;

    @BeforeEach
    public void setUp(){
        questionRepository = mock(QuestionRepository.class);
        mapperUtils = new MapperUtils();
        updateUseCase = new UpdateUseCase(mapperUtils,questionRepository);

    }

    @Test
    public void setUpdateUseCase() {
        var question = new Question();
        question.setId("question1");
        question.setUserId("user1");
        question.setQuestion("¿question?");
        question.setType("cerrada");
        question.setCategory("matematicas");

        var questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setUserId(question.getUserId());
        questionDTO.setQuestion(question.getQuestion());
        questionDTO.setType("abierta");
        questionDTO.setCategory("quimica");


        when(questionRepository.save(Mockito.any(Question.class))).thenReturn(Mono.just(question));

        StepVerifier.create(updateUseCase.apply(questionDTO))

                .expectNextMatches(q -> {

                    assert questionDTO.getId().equalsIgnoreCase(question.getId());
                    assert questionDTO.getUserId().equalsIgnoreCase("user1");
                    assert questionDTO.getQuestion().equalsIgnoreCase("¿question?");
                    assert questionDTO.getType().equalsIgnoreCase("abierta");
                    assert questionDTO.getCategory().equalsIgnoreCase("quimica");
                    return true;
                }).verifyComplete();

        verify(questionRepository).save(Mockito.any(Question.class));
    }

}