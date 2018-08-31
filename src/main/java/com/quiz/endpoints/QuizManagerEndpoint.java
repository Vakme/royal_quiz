package com.quiz.endpoints;

import com.quiz.controllers.QuizManagerController;
import com.quiz.models.db.Question;
import com.quiz.models.db.Quiz;
import com.quiz.models.db.Result;
import com.quiz.models.rest.QuizPackage;
import com.quiz.models.rest.QuizURI;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

/**
 * Endpoint used for basic operations on quizzes
 */
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

    /**
     * Service used to add new quiz
     * @param quizPackage New quiz data
     * @return QuizURI object with UUID
     */
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

    /**
     * Service used to get full data of single quiz
     * @param uuid unique quiz UUID
     */
    @GET
    @Path("/get/{uuid}")
    public Response getQuiz(@PathParam("uuid") UUID uuid) {
        Quiz quiz = controller.get(uuid);
        System.out.println(quiz);
        return Response.ok().entity(quiz).build();
    }

    /**
     * Service used to get list with basic info of public quizzes
     */
    @GET
    @Path("/getPublic")
    public Response getPublic() {
        List<Quiz> quizzes = controller.getPublic();
        return Response.ok().entity(quizzes).build();
    }

    /**
     * Service used to get list of quiz types
     */
    @GET
    @Path("/constants")
    public Response constants() {
        return Response.ok().entity(Quiz.Type.values()).build();
    }
}
