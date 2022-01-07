package co.com.sofka.questions.useCases;

import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.utils.MapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class FindAllByCategoryUseCase implements Function<String, Flux<QuestionDTO>> {
    private final QuestionRepository questionRepository;
    private final MapperUtils mapperUtils;

    public FindAllByCategoryUseCase(MapperUtils mapperUtils, QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
        this.mapperUtils = mapperUtils;
    }

    public Flux<QuestionDTO> apply(String category) {
        Objects.requireNonNull(category, "Category is required");
        return questionRepository.findAllByCategory(category)
                .map(mapperUtils.mapEntityToQuestion());
    }
}
