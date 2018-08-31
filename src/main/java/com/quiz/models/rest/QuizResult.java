package com.quiz.models.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Single result of the quiz
 * Contains result content and the final weight this result scored
 */
public class QuizResult {

    @JsonProperty("category")
    private String content;

    private Double weight;

    public QuizResult(String content, Double weight) {
        this.content = content;
        this.weight = weight;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
