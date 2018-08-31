package com.quiz.controllers;

import com.quiz.db.DbManager;
import com.quiz.models.db.Answer;
import com.quiz.models.db.Question;
import com.quiz.models.db.Quiz;
import com.quiz.models.db.Result;
import com.quiz.models.rest.QuizResult;
import com.quiz.models.rest.QuizResultMap;
import com.quiz.models.rest.SolvedQuiz;
import org.decimal4j.util.DoubleRounder;

import java.util.*;
import java.util.stream.Collectors;

//TODO: Refactor

/**
 * Solver for quizzes with type CATEGORY
 */
public class CategoryQuizSolverController implements QuizSolverController {
    @Override
    public QuizResultMap solve(SolvedQuiz solvedQuiz) {

        DbManager<Quiz> db = new DbManager<>();
        List<Quiz> quizzes = db.findEqualByParam(Quiz.class, new AbstractMap.SimpleEntry<>("uuid", UUID.class), solvedQuiz.getUuid());
        Quiz quiz = quizzes.get(0);
        Map<Integer, Integer> solution = solvedQuiz.getSolution();
        List<Question> questions = quiz.getQuestions();
        List<Answer> answers = questions.stream().map(question -> filterAnswer(solution, question)).collect(Collectors.toList());
        answers.forEach(System.out::println);
        Map<Result, Double> categories = new HashMap<>();
        answers.forEach(answer ->
                answer
                        .getResults()
                        .forEach(resultAnswer ->
                            categories.compute(resultAnswer.getResultid(),
                                    (result, weight) -> weight == null ? resultAnswer.getWeight() : weight+resultAnswer.getWeight())
                        ));
        categories.forEach((k, v) -> System.out.println(k+" "+v));
        List<QuizResult> results = new ArrayList<>();
        categories.forEach((result, weight) -> results.add(new QuizResult(result.getContent(), mapWeight(weight, solution))));
        db.close();
        return new QuizResultMap(results);
    }

    /**
     * Calculate final weight and round to 2 decimal points
     * @param weight Sum of weights
     * @param solution Solution map
     * @return final score
     */
    private double mapWeight(double weight, Map<Integer, Integer> solution) {
        double length = solution.keySet().size();
        return DoubleRounder.round(weight/length, 2);
    }

    /**
     * Filter only the answers that user sumbitted
     * @param solution solution map
     * @param question question to be filtered
     * @return
     */
    private Answer filterAnswer(Map<Integer, Integer> solution, Question question) {
        solution.forEach((q, a) -> System.out.println(a + " qs:" + q));
        question.getAnswers()
                .forEach(answer -> System.out.println(answer.getNum() + " q:" + solution.get(question.getNum())));

        return question
                .getAnswers()
                .stream()
                .filter(answer -> answer.getNum() == solution.get(question.getNum()))
                .findFirst()
                .orElse(new Answer());
    }
}
