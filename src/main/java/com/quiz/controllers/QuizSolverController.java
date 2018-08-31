package com.quiz.controllers;

import com.quiz.models.rest.QuizResultMap;
import com.quiz.models.rest.SolvedQuiz;

public interface QuizSolverController {
    QuizResultMap solve(SolvedQuiz quiz);

}
