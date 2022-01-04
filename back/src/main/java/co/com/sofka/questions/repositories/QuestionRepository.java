package co.com.sofka.questions.repositories;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.utils.Category;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface QuestionRepository extends ReactiveCrudRepository<Question, String> {
    Flux<Question> findByUserId(String userId);
    Flux<Question> findAllByCategory(String category);
}
