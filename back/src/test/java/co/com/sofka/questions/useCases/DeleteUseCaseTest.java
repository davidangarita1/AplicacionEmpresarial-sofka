package co.com.sofka.questions.useCases;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.repositories.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

@SpringBootTest
class DeleteUseCaseTest {
    @MockBean
    private AnswerRepository answerRepository;
    @MockBean
    private QuestionRepository questionRepository;

    @SpyBean
    DeleteUseCase deleteQuestionUseCase;

    @Test
    void deleteUseCaseTest(){

        var answerDTO = new AnswerDTO("1asd2153453", "123", "What id DDD in software");

        var answer = new Answer("1asd2153453", "123", "1", "What id DDD in software", 1);

        Mockito.when(questionRepository.deleteById("1asd2153453")).thenReturn(Mono.empty());
        Mockito.when(answerRepository.deleteByQuestionId("1asd2153453")).thenReturn(Mono.empty());

        var result = deleteQuestionUseCase.apply("1asd2153453").block();
        Assertions.assertNull(result);
    }
}