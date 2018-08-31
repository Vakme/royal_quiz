package com.quiz.controllers;

import com.github.slugify.Slugify;
import com.quiz.db.DbManager;
import com.quiz.models.db.*;
import com.quiz.models.rest.QuizPackage;
import com.quiz.models.rest.QuizURI;

import java.util.AbstractMap;
import java.util.ArrayList;
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
        answerDbManager.close();
    }

    private void addQuiz(Quiz quiz) {
        DbManager<Quiz> db = new DbManager<>();
        db.insertSingle(quiz);
        db.close();
    }

    private void addQuestions(List<Question> questions, List<Result> results) {
        DbManager<Question> questionDbManager = new DbManager<>();
        questionDbManager.insertMany(questions);
        questions.forEach(question -> question.getAnswers().forEach(answer -> answer.setQuestion(question)));
        questions.forEach(question -> addAnswers(question.getAnswers(), results));
        questionDbManager.close();
    }

    private void addAnswers(List<Answer> answers, List <Result> results) {
        DbManager<Answer> answerDbManager = new DbManager<>();
        answerDbManager.insertMany(answers);
        connectAnswerToResult(answers, results);
        answerDbManager.close();

    }

    public QuizURI addQuiz(Quiz quiz, List<Result> results) {
        quiz.setUuid(UUID.randomUUID());
        addQuiz(quiz);
        quiz.getQuestions().forEach(question -> question.setQuiz(quiz));
        addQuestions(quiz.getQuestions(),results);
        return new QuizURI(quiz.getUuid(), quiz.getName(), createSlug(quiz.getName()));
    }

    public void addResults(List<Result> results) {
        DbManager<Result> db = new DbManager<>();
        db.insertMany(results);
        db.close();
    }

    public Quiz get(UUID uuid) {
        DbManager<Quiz> db = new DbManager<>();
        List<Quiz> quizzes = db.findEqualByParam(Quiz.class, new AbstractMap.SimpleEntry<>("uuid", UUID.class), uuid);
        Quiz quiz = quizzes.get(0);
        quiz.setUserinfo(new User(quiz.getUserinfo().getLogin()));
        db.close();
        return quiz;
    }


    public List<Quiz> getPublic() {
        DbManager<Quiz> db = new DbManager<>();
        List<Quiz> quizzes = db.findEqualByParam(Quiz.class, new AbstractMap.SimpleEntry<>("privacy", Quiz.Privacy.class), Quiz.Privacy.PUBLIC);
        List<Quiz> shortQuizzes = new ArrayList<>();
        quizzes.forEach(quiz -> {
            assert quiz.getUserinfo() != null;
            shortQuizzes.add(
                    new Quiz(
                            quiz.getName(),
                            quiz.getPrivacy(),
                            quiz.getType(),
                            quiz.getUuid(),
                            quiz.getRating(),
                            quiz.getUserinfo() == null ? null :
                            new User(quiz.getUserinfo().getLogin())
                    ));
        });
        db.close();
        return shortQuizzes;
    }
}
