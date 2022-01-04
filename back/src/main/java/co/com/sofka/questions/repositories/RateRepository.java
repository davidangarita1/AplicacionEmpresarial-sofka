package co.com.sofka.questions.repositories;

import co.com.sofka.questions.collections.Rate;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface RateRepository extends ReactiveCrudRepository<Rate, String> {

    Mono<Rate> findByUserIdAndAnswerId (String userId,String answerId);
    
}
