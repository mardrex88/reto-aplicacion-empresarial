package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class ListUseCaseTest {

     QuestionRepository repository;
     ListUseCase listUseCase;


    @BeforeEach
    public void setup(){
        MapperUtils mapperUtils = new MapperUtils();
        repository = mock(QuestionRepository.class);
        listUseCase = new ListUseCase(mapperUtils, repository);
    }

    @Test
     void getValidationTest(){
        var question =  new Question();
        question.setUserId("xxxx-xxxx");
        question.setType("tech");
        question.setCategory("software");
        question.setQuestion("¿Que es java?");
        when(repository.findAll()).thenReturn(Flux.just(question ));

        StepVerifier.create(listUseCase.get())
                .expectNextMatches(questionDTO -> {
                    assert questionDTO.getUserId().equals("xxxx-xxxx");
                    assert questionDTO.getCategory().equals("software");
                    assert questionDTO.getQuestion().equals("¿Que es java?");
                    assert questionDTO.getType().equals("tech");
                    return true;
                })
                .verifyComplete();

        verify(repository).findAll();
    }

    @Test
    void countValidationTest(){
        var question =  new Question();
        question.setUserId("xxxx-xxxx");
        question.setType("tech");
        question.setCategory("Marlon");
        question.setQuestion("¿Que es java?");

        when(repository.findAll()).thenReturn(Flux.just(question,question,question));
    //    when(repository.findAll().count()).thenReturn(Mono.just(1L));

        StepVerifier.create(listUseCase.count())
             .expectNextMatches(questionDTO -> {
                 Assertions.assertEquals(questionDTO,3L);
                 return true;
                })
                .verifyComplete();

        verify(repository).findAll();
    }
    @Test
    void getCountQuestions(){

        var question =  new Question();
        question.setUserId("xxxx-xxxx");
        question.setType("tech");
        question.setCategory("Camila");
        question.setQuestion("¿Que es java?");

        var question2 =  new Question();
        question2.setUserId("xxxx-xxxx2");
        question2.setType("tech");
        question2.setCategory("software");
        question2.setQuestion("¿Que es java?");

        when(repository.findAll()).thenReturn(Flux.just(question, question2, question2));


        StepVerifier.create(listUseCase.count())
                .expectNext(3L)
                .verifyComplete();

        verify(repository).findAll();
    }
}

