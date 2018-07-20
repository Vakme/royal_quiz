package com.quiz.endpoints;

import com.quiz.controllers.QuizManagerController;
import com.quiz.models.Answer;
import com.quiz.models.Quiz;
import com.quiz.models.Result;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Produces("application/json")
@Path("/manage")
public class QuizManagerEndpoint {

    private QuizManagerController controller;

    public QuizManagerEndpoint() {
        controller = new QuizManagerController();
    }

    public QuizManagerEndpoint(QuizManagerController controller) {

        this.controller = controller;
    }

    @POST
    @Path("/addQuiz")
    public Response addQuiz(Quiz quiz) {
        UUID uuid = controller.addQuiz(quiz);
        return Response.ok().entity(uuid).build();
    }

    @POST
    @Path("/addAnswers")
    public Response addAnswers(List<Answer> answers) {
        return Response.ok().build();
    }

    @POST
    @Path("/addResults")
    public Response addResults(List<Result> results) {
        return Response.ok().build();
    }
}
