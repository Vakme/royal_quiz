package com.quiz.controllers;

import com.quiz.db.DbManager;
import com.quiz.models.Answer;
import com.quiz.models.Quiz;
import com.quiz.models.Result;

import java.util.List;
import java.util.UUID;

public class QuizManagerController {

    public QuizManagerController() {

    }

    public UUID addQuiz(Quiz quiz) {
        DbManager<Quiz> db = new DbManager<>();
        quiz.setUuid(UUID.randomUUID());
        db.insertSingle(quiz);
        return quiz.getUuid();
    }

    public void addAnswers(List<Answer> answers) {

    }

    public void addResults(List<Result> results) {

    }
}
