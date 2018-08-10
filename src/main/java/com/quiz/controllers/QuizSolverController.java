package com.quiz.controllers;

import com.quiz.models.rest.QuizResult;
import com.quiz.models.rest.SolvedQuiz;

import java.util.List;

public interface QuizSolverController {
    List<QuizResult> solve(SolvedQuiz quiz);

}
