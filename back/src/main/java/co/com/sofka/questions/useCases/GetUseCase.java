package co.com.sofka.questions.useCases;

import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.repositories.RateRepository;
import co.com.sofka.questions.utils.MapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class GetUseCase implements Function<String, Mono<QuestionDTO>> {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final MapperUtils mapperUtils;
    private final RateRepository rateRepository;

    public GetUseCase(MapperUtils mapperUtils, QuestionRepository questionRepository, AnswerRepository answerRepository, RateRepository rateRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.mapperUtils = mapperUtils;
        this.rateRepository = rateRepository;
    }

    @Override
    public Mono<QuestionDTO> apply(String id) {
        Objects.requireNonNull(id, "Id is required");
        return questionRepository.findById(id)
                .map(mapperUtils.mapEntityToQuestion())
                .flatMap(mapQuestionAggregate());
    }

    private Function<QuestionDTO, Mono<QuestionDTO>> mapQuestionAggregate() {
        return questionDTO ->
                Mono.just(questionDTO).zipWith(
                        answerRepository.findAllByQuestionId(questionDTO.getId())
                                .map(mapperUtils.mapEntityToAnswer())
                                .collectList(),
                        (question, answers) -> {
                            question.setAnswers(answers);
                            return question;
                        }

                );
    }

    private Function<AnswerDTO, Mono<AnswerDTO>> mapAnswerAggregate() {
        return answerDTO ->
                Mono.just(answerDTO).zipWith(
                        rateRepository.findAllByAnswerId(answerDTO.getId())
                                .map(mapperUtils.mapEntityToRate())
                                .collectList(),
                        (answer, rates) -> {
                            answer.setRates(rates);
                            return answer;
                        }
                );
    }

}
