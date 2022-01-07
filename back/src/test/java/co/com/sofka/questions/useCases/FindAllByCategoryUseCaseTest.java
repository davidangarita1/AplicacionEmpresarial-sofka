package co.com.sofka.questions.useCases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.utils.Category;
import co.com.sofka.questions.utils.MapperUtils;
import co.com.sofka.questions.utils.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class FindAllByCategoryUseCaseTest {

    @MockBean
    QuestionRepository repository;

    @SpyBean
    FindAllByCategoryUseCase findAllByCategory;

    @BeforeEach
    public void setup(){
        MapperUtils mapperUtils = new MapperUtils();
        repository = mock(QuestionRepository.class);
        findAllByCategory = new FindAllByCategoryUseCase(mapperUtils, repository);
    }

    @Test
    void findAllByCategoryUseCaseTest() {
        var question = new Question("1", "1234", "What id DDD in software?", Type.OPEN, Category.SOFTWARE_DEVELOPMENT);
        when(repository.findAllByCategory("SOFTWARE_DEVELOPMENT")).thenReturn(Flux.just(question));

        StepVerifier.create(findAllByCategory.apply("SOFTWARE_DEVELOPMENT"))
                .expectNextMatches(questionDTO -> {
                    assert questionDTO.getUserId().equals("1234");
                    assert questionDTO.getCategory().equals(Category.SOFTWARE_DEVELOPMENT);
                    assert questionDTO.getQuestion().equals("What id DDD in software?");
                    assert questionDTO.getType().equals(Type.OPEN);
                    return true;
                })
                .verifyComplete();

        verify(repository).findAllByCategory("SOFTWARE_DEVELOPMENT");
    }
}