package co.com.sofka.questions.model;


import co.com.sofka.questions.collections.Rate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

public class AnswerDTO {
    @NotBlank
    private String userId;
    @NotBlank
    private String questionId;
    @NotBlank
    @Size(min = 15, max = 250, message = "Error de tamaño de caracteres")
    private String answer;
    private Integer position;
    private List<Rate> rates;

    public AnswerDTO() {

    }

    public AnswerDTO(@NotBlank String questionId, @NotBlank String userId, @NotBlank String answer) {
        this.userId = userId;
        this.questionId = questionId;
        this.answer = answer;
    }

    public List<Rate> getRates() {
        this.rates = Optional.ofNullable(rates).orElse(new ArrayList<>());
        return rates;
    }

    public Integer getPosition() {
        return Optional.ofNullable(position).orElse(1);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerDTO answerDTO = (AnswerDTO) o;
        return Objects.equals(userId, answerDTO.userId) && Objects.equals(questionId, answerDTO.questionId) && Objects.equals(answer, answerDTO.answer) && Objects.equals(position, answerDTO.position) && Objects.equals(rates, answerDTO.rates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, questionId, answer, position, rates);
    }

    @Override
    public String toString() {
        return "AnswerDTO{" +
                "userId='" + userId + '\'' +
                ", questionId='" + questionId + '\'' +
                ", answer='" + answer + '\'' +
                ", position=" + position +
                ", rates=" + rates +
                '}';
    }
}
