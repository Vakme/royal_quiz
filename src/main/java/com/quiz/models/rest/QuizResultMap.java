package com.quiz.models.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * List of quiz results
 * Sent via API after final score for each result is calculated
 */
public class QuizResultMap {

    @JsonProperty("results")
    private List<QuizResult> map;

    public QuizResultMap(List<QuizResult> map) {
        this.map = map;
    }

    public List<QuizResult> getMap() {
        return map;
    }

    public void setMap(List<QuizResult> map) {
        this.map = map;
    }
}
