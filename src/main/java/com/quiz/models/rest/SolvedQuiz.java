package com.quiz.models.rest;

import com.quiz.models.db.Quiz;

import java.util.Map;
import java.util.UUID;

public class SolvedQuiz {
    private UUID uuid;
    private Map<Integer, Integer> solution;

    public Quiz.Type getType() {
        return type;
    }

    public void setType(Quiz.Type type) {
        this.type = type;
    }

    private Quiz.Type type;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Map<Integer, Integer> getSolution() {
        return solution;
    }

    public void setSolution(Map<Integer, Integer> solution) {
        this.solution = solution;
    }
}
