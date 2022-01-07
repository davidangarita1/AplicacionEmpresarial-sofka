package co.com.sofka.questions.useCases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.utils.Category;
import co.com.sofka.questions.utils.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;
import static org.mockito.Mockito.when;
import java.util.Objects;

@SpringBootTest
class UpdateUseCaseTest {

    @SpyBean
    private UpdateUseCase updateUseCase;

    @MockBean
    private QuestionRepository repository;

    @Test
    void updateUseCaseTest(){

        var questionDT0 = new QuestionDTO("1", "1234", "What id DDD in software?", Type.OPEN, Category.SOFTWARE_DEVELOPMENT);

        var question = new Question("1", "1234", "What id DDD in software?", Type.OPEN, Category.SOFTWARE_DEVELOPMENT);

        when(repository.save(Mockito.any(Question.class))).thenReturn(Mono.just(question));

        var result = updateUseCase.apply(questionDT0);

        Assertions.assertEquals(Objects.requireNonNull(result.block()),"1");
    }
}