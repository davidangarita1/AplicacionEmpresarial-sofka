package co.com.sofka.questions.repositories;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.Rate;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RateRepository extends ReactiveCrudRepository<Rate, String> {
    Flux<Rate> findAllByAnswerId(String id);
    Mono<Rate> findByUserIdAndAnswerId (String userId,String answerId);
    
}
