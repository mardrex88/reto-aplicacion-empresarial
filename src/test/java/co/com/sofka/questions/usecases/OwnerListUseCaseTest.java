package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


import static org.mockito.Mockito.*;


class OwnerListUseCaseTest {

    QuestionRepository questionRepository;
    OwnerListUseCase ownerListUseCase;

    @BeforeEach
    public void setup() {
        MapperUtils mapperUtils = new MapperUtils();
        questionRepository = mock(QuestionRepository.class);
        ownerListUseCase = new OwnerListUseCase(mapperUtils, questionRepository);
    }

    @Test
    void getValidationTest() {

        var questionDTO = new QuestionDTO("pregunta1","user1","what is book","cerrada","ingles");

        var question = new Question();
        question.setId("pregunta1");
        question.setUserId("user1");
        question.setType("cerrada");
        question.setCategory("ingles");
        question.setQuestion("what is book");

        when(questionRepository.save(Mockito.any(Question.class))).thenReturn(Mono.just(question));

        when(questionRepository.findByUserId(questionDTO.getUserId())).thenReturn(Flux.just(question));

        StepVerifier.create(ownerListUseCase.apply(question.getUserId()).collectList())
                .expectNextMatches(preguntas -> {
                    assert preguntas.get(0).getId().equals(question.getId());
                    assert preguntas.get(0).getUserId().equals(question.getUserId());
                    assert preguntas.get(0).getType().equals(question.getType());
                    assert preguntas.get(0).getCategory().equals(question.getCategory());
                    assert preguntas.get(0).getQuestion().equals(question.getQuestion());
                    return true;
                }).verifyComplete();

        verify(questionRepository).findByUserId(question.getUserId());
    }
}