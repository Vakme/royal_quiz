package com.quiz.controllers;

import com.quiz.models.rest.QuizResultMap;
import com.quiz.models.rest.SolvedQuiz;

/**
 * Solver for quizzes with type TEST
 */
public class TestQuizSolverController implements QuizSolverController {
    @Override
    public QuizResultMap solve(SolvedQuiz quiz) {
        return null;
    }
}
