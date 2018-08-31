package com.quiz.controllers;

import com.github.slugify.Slugify;
import com.quiz.db.DbManager;
import com.quiz.models.db.*;
import com.quiz.models.rest.QuizURI;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Controlled used to perform basic quiz operations
 */
public class QuizManagerController {

    public QuizManagerController() {

    }

    /**
     * function used to slugify quiz name
     * @param name Name of quiz
     * @return Slug
     */
    private String createSlug(String name) {
        Slugify slg = new Slugify();
        return slg.slugify(name);
    }

    /**
     * Method used to connect answers and results in database
     * @param answers answers to be connected
     * @param results results to be connected
     */
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

    /**
     * Method used to insert quiz to db
     * @param quiz quiz to be persisted
     */
    private void addQuiz(Quiz quiz) {
        DbManager<Quiz> db = new DbManager<>();
        db.insertSingle(quiz);
        db.close();
    }

    /**
     * Method used to insert questions to db
     * @param questions questions to be persisted
     * @param results results to be connected to answers
     */
    private void addQuestions(List<Question> questions, List<Result> results) {
        DbManager<Question> questionDbManager = new DbManager<>();
        questionDbManager.insertMany(questions);
        questions.forEach(question -> question.getAnswers().forEach(answer -> answer.setQuestion(question)));
        questions.forEach(question -> addAnswers(question.getAnswers(), results));
        questionDbManager.close();
    }

    /**
     * Method used to insert answers to db
     * @param answers answers to be persisted
     * @param results results to be connected to answers
     */
    private void addAnswers(List<Answer> answers, List <Result> results) {
        DbManager<Answer> answerDbManager = new DbManager<>();
        answerDbManager.insertMany(answers);
        connectAnswerToResult(answers, results);
        answerDbManager.close();

    }

    /**
     * Method used to create UUID and manage the process of quiz addition
     * @param quiz Quiz to be persisted
     * @param results Results to be persisted
     * @return QuizURI object with UUID
     */
    public QuizURI addQuiz(Quiz quiz, List<Result> results) {
        quiz.setUuid(UUID.randomUUID());
        addQuiz(quiz);
        quiz.getQuestions().forEach(question -> question.setQuiz(quiz));
        addQuestions(quiz.getQuestions(),results);
        return new QuizURI(quiz.getUuid(), quiz.getName(), createSlug(quiz.getName()));
    }

    /**
     * Method used to insert results to db
     * @param results results to be persisted
     */
    public void addResults(List<Result> results) {
        DbManager<Result> db = new DbManager<>();
        db.insertMany(results);
        db.close();
    }

    /**
     * Method used to select chosen quiz from db
     * @param uuid Unique UUID
     * @return Selected quiz
     */
    public Quiz get(UUID uuid) {
        DbManager<Quiz> db = new DbManager<>();
        List<Quiz> quizzes = db.findEqualByParam(Quiz.class, new AbstractMap.SimpleEntry<>("uuid", UUID.class), uuid);
        Quiz quiz = quizzes.get(0);
        quiz.setUserinfo(new User(quiz.getUserinfo().getLogin()));
        db.close();
        return quiz;
    }

    /**
     * Method used to select list of public quizzes
     * @return list of quizzes
     */
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
