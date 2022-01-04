package co.com.sofka.questions.useCases;

import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.model.RateDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveRate {
    Mono<String> apply(@Valid RateDTO rateDTO);
}
