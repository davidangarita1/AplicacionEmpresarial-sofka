package co.com.sofka.questions.useCases;

import co.com.sofka.questions.collections.Rate;
import co.com.sofka.questions.model.RateDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.repositories.RateRepository;
import co.com.sofka.questions.utils.MapperUtils;
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

    public Mono<String> apply(RateDTO rateDTO) {
        Objects.requireNonNull(rateDTO.getUserId(), "IdUser of the rate is required");
        Objects.requireNonNull(rateDTO.getAnswerId(), "IdAnswer of the rate is required");

     return   rateRepository.findByUserIdAndAnswerId(rateDTO.getUserId(),rateDTO.getAnswerId())
                .switchIfEmpty(rateRepository.save(mapperUtils.mapperToRate().apply(rateDTO))
                ).map(Rate::getId);


    }
}





    /*return getUseCase.apply(rateDTO.getAnswerId()).flatMap(question ->
                answerRepository.save(mapperUtils.mapperToAnswer().apply(rateDTO))
                        .map(answer -> {
                            question.getAnswers().add(rateDTO);
                            return question;
                        })
        );*/

/*.flatMap(
 *//*  rate -> {
                            answerRepository.findById(rateDTO.getAnswerId()).map( aw->{
                                        aw.setPosition(rate.getRate());
                                        return answerRepository.save(aw);
                                    }
                            );
                            return Mono.just("oe");
                        }*/