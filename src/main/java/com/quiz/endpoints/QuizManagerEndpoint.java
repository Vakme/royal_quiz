package com.quiz.endpoints;

import com.quiz.controllers.QuizManagerController;
import com.quiz.models.db.Question;
import com.quiz.models.db.Quiz;
import com.quiz.models.db.Result;
import com.quiz.models.rest.QuizPackage;
import com.quiz.models.rest.QuizURI;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

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
    public Response addQuiz(QuizPackage quizPackage) {
        controller.addResults(quizPackage.getResults());
        QuizURI quizURI = controller.addQuiz(quizPackage.getQuiz(), quizPackage.getResults());
        return Response.ok().entity(quizURI).build();
    }

    @POST
    @Path("/addAnswers")
    public Response addQuestions(List<Question> answers) {
        return Response.ok().build();
    }

    @POST
    @Path("/addResults")
    public Response addResults(List<Result> results) {
        return Response.ok().build();
    }
}
