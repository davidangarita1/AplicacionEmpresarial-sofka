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
import static org.mockito.Mockito.*;

@SpringBootTest
class OwnerListUseCaseTest {

    @MockBean
    QuestionRepository repository;

    @SpyBean
    OwnerListUseCase ownerListUseCase;

    @BeforeEach
    public void setup(){
        MapperUtils mapperUtils = new MapperUtils();
        repository = mock(QuestionRepository.class);
        ownerListUseCase = new OwnerListUseCase(mapperUtils, repository);
    }

    @Test
    void ownerListUseCaseTest() {
        var question = new Question("1", "1234", "What id DDD in software", Type.OPEN, Category.SOFTWARE_DEVELOPMENT);
        when(repository.findByUserId(question.getUserId())).thenReturn(Flux.just(question));

        StepVerifier.create(ownerListUseCase.apply(question.getUserId()))
                .expectNextMatches(questionDTO -> {
                    assert questionDTO.getUserId().equals("1234");
                    assert questionDTO.getCategory().equals(Category.SOFTWARE_DEVELOPMENT);
                    assert questionDTO.getQuestion().equals("What id DDD in software");
                    assert questionDTO.getType().equals(Type.OPEN);
                    return true;
                })
                .verifyComplete();

        verify(repository).findByUserId(question.getUserId());
    }
}