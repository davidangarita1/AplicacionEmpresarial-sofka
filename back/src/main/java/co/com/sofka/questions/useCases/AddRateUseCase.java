package co.com.sofka.questions.useCases;

import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.model.RateDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.repositories.RateRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class AddRateUseCase implements SaveRate{
    private final RateRepository rateRepository;
    private final AnswerRepository answerRepository;
    private final MapperUtils mapperUtils;
    private final GetUseCase getUseCase;

    public AddRateUseCase(MapperUtils mapperUtils, GetUseCase getUseCase, RateRepository rateRepository, AnswerRepository answerRepository) {
        this.rateRepository = rateRepository;
        this.getUseCase = getUseCase;
        this.mapperUtils = mapperUtils;
        this.answerRepository = answerRepository;
    }

    public Mono<QuestionDTO> apply(RateDTO rateDTO) {
        Objects.requireNonNull(rateDTO.getUserId(), "IdUser of the rate is required");
        Objects.requireNonNull(rateDTO.getAnswerId(), "IdAnswer of the rate is required");

        return answerRepository.

        /*return getUseCase.apply(rateDTO.getAnswerId()).flatMap(question ->
                answerRepository.save(mapperUtils.mapperToAnswer().apply(rateDTO))
                        .map(answer -> {
                            question.getAnswers().add(rateDTO);
                            return question;
                        })
        );*/
    }
}
