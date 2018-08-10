package com.quiz.models.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.quiz.models.db.Quiz;
import com.quiz.models.db.Result;

import java.util.List;

public class QuizPackage {

    private Quiz quiz;
    private List<Result> results;

    public QuizPackage () {

    }

    public QuizPackage(Quiz quiz, List<Result> results) {
        this.quiz = quiz;
        this.results = results;
    }
    @JsonProperty("quiz")
    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
    @JsonProperty("results")
    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
