package com.quiz.controllers;

import com.github.slugify.Slugify;
import com.quiz.db.DbManager;
import com.quiz.models.db.*;
import com.quiz.models.rest.QuizPackage;
import com.quiz.models.rest.QuizURI;

import java.util.List;
import java.util.UUID;

public class QuizManagerController {

    public QuizManagerController() {

    }

    private String createSlug(String name) {
        Slugify slg = new Slugify();
        return slg.slugify(name);
    }

    private void connectAnswerToResult(List<Answer> answers, List<Result> results) {
        DbManager<ResultAnswer> answerDbManager = new DbManager<>();
        answers.forEach(answer -> {
            results.forEach(result -> {
                answer.getResults().forEach(resultAnswer -> {
                    if(resultAnswer.getResultNumber() == result.getResultNumber()) {
                        resultAnswer.setAnswerid(answer);
                        resultAnswer.setResultid(result);

                    }
                });
            });
            answerDbManager.insertMany(answer.getResults());
        });
    }

    public QuizURI addQuiz(Quiz quiz, List<Result> results) {
        DbManager<Quiz> db = new DbManager<>();
        quiz.setUuid(UUID.randomUUID());
        db.insertSingle(quiz);
        DbManager<Question> questionDbManager = new DbManager<>();
        quiz.getQuestions().forEach(question -> question.setQuiz(quiz));
        questionDbManager.insertMany(quiz.getQuestions());
        quiz.getQuestions().forEach(question -> question.getAnswers().forEach(answer -> answer.setQuestion(question)));
        DbManager<Answer> answerDbManager = new DbManager<>();
        quiz.getQuestions().forEach(question -> answerDbManager.insertMany(question.getAnswers()));
        quiz.getQuestions().forEach(question -> connectAnswerToResult(question.getAnswers(), results));

        return new QuizURI(quiz.getUuid(), quiz.getName(), createSlug(quiz.getName()));
    }

    public void addQuestions(List<Question> questions) {

    }

    public void addResults(List<Result> results) {
        DbManager<Result> db = new DbManager<>();
        db.insertMany(results);
    }
}
