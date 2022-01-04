package co.com.sofka.questions.repositories;

import co.com.sofka.questions.collections.Rate;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface RateRepository extends ReactiveCrudRepository<Rate, String> {
    
}
