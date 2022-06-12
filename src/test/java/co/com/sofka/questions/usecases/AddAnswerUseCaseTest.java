package co.com.sofka.questions.usecases;
import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AddAnswerUseCaseTest {

    QuestionRepository questionRepository;
    AnswerRepository answerRepository;
    AddAnswerUseCase addAnswerUseCase;
    GetUseCase getUseCase;


    @BeforeEach
    public void setup() {
        MapperUtils mapperUtils = new MapperUtils();
        questionRepository = mock(QuestionRepository.class);
        answerRepository = mock(AnswerRepository.class);
        getUseCase = new GetUseCase(mapperUtils, questionRepository, answerRepository);
        addAnswerUseCase = new AddAnswerUseCase(mapperUtils, getUseCase, answerRepository);
    }

    @Test
    public void addAnswerTest() {
        MapperUtils mapper = new MapperUtils();
        var answerTest = new Answer();
        answerTest.setId("ans1");
        answerTest.setUserId("user1");
        answerTest.setQuestionId("question1");
        answerTest.setAnswer("Responseanswer 1");
        answerTest.setPosition(0);


        var answerDTOTest = mapper.mapEntityToAnswer().apply(answerTest);
        var questionTest = new Question();
        questionTest.setId("question1");
        questionTest.setUserId("user1");
        questionTest.setQuestion("esta es la question");
        questionTest.setType("abierta");
        questionTest.setCategory("quimica");

        when(questionRepository.findById(any(String.class))).thenReturn(Mono.just(questionTest));
        when(answerRepository.save(any(Answer.class))).thenReturn(Mono.just(answerTest));

        when(answerRepository.findAllByQuestionId(any(String.class))).thenReturn(Flux.empty());


        StepVerifier.create(addAnswerUseCase.apply(answerDTOTest))
                .expectNextMatches(questiondto -> {
                    assert questiondto.getId().equals("question1");
                    assert questiondto.getUserId().equals("user1");
                    assert questiondto.getQuestion().equals("esta es la question");
                    assert questiondto.getType().equals("abierta");
                    assert questiondto.getCategory().equals("quimica");
                    assert questiondto.getAnswers().contains(answerDTOTest);
                    return true;
                })
                .verifyComplete();

        verify(questionRepository).findById("ans1");
        verify(answerRepository).findAllByQuestionId("question1");


    }


}