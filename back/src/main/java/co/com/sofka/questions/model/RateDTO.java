package co.com.sofka.questions.model;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class RateDTO {
    private String id;
    @NotBlank
    private String userId;
    @NotBlank
    private String answerId;
    @Range(min = -1, max = 1, message = "Error Rate")
    private Integer rate;

    public RateDTO() {
    }

    public RateDTO(String id, String userId, String answerId, Integer rate) {
        this.id = id;
        this.userId = userId;
        this.answerId = answerId;
        this.rate = rate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RateDTO rateDTO = (RateDTO) o;
        return Objects.equals(id, rateDTO.id) && Objects.equals(userId, rateDTO.userId) && Objects.equals(answerId, rateDTO.answerId) && Objects.equals(rate, rateDTO.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, answerId, rate);
    }

    @Override
    public String toString() {
        return "RateDTO{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", questionId='" + answerId + '\'' +
                ", rate=" + rate +
                '}';
    }
}
