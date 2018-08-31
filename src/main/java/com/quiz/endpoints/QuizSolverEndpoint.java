package com.quiz.endpoints;

import com.quiz.controllers.CategoryQuizSolverController;
import com.quiz.controllers.QuizSolverController;
import com.quiz.controllers.TestQuizSolverController;
import com.quiz.models.rest.QuizResult;
import com.quiz.models.rest.QuizResultMap;
import com.quiz.models.rest.SolvedQuiz;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

/**
 * Endpoint used for solving a quiz
 */
@Produces("application/json")
@Path("/solve")
public class QuizSolverEndpoint {

    private QuizSolverController controller = null;

    public QuizSolverEndpoint() {
    }

    public QuizSolverEndpoint(QuizSolverController controller) {
        this.controller = controller;
    }

    /**
     * Service used to pass answers to a solver based on quiz type
     * @param quiz map of user's answers
     */
    @POST
    @Path("/")
    public Response solve(SolvedQuiz quiz) {
        quiz.getSolution().forEach((k, v) -> System.out.println(k + " " + v));
        switch (quiz.getType()) {
            case TEST:
                controller = new TestQuizSolverController();
                break;
            case CATEGORY:
                controller = new CategoryQuizSolverController();
                break;
        }
        QuizResultMap result = controller.solve(quiz);
        return Response.ok().entity(result).build();
    }
}
