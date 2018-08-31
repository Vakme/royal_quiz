package com.quiz.endpoints;
import com.quiz.annotations.RestrictedContentAnnotation;
import com.quiz.controllers.LoginController;
import com.quiz.models.db.User;

import javax.validation.ValidationException;
import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

@Produces("application/json")
@Path("/auth")
public class LoginEndpoint {

    private LoginController controller;

    public LoginEndpoint() {
        controller = new LoginController();
    }

    public LoginEndpoint(LoginController controller) {

        this.controller = controller;
    }

    @POST
    @Path("/register")
    public Response register(User user) {
        try {
            controller.register(user);
            return Response.ok().build();
        }
        catch (ValidationException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        catch (Exception e) {
            return Response.serverError().build();
        }
    }


    @POST
    @Path("/login")
    public Response login(User user) {
        try {
            NewCookie authCookie = controller.login(user);
            return Response.ok().cookie(authCookie).build();
        }
        catch (ValidationException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/logout")
    @RestrictedContentAnnotation
    public Response logout(@CookieParam("authToken") Cookie authToken) {
        return Response.ok().cookie(controller.logout(authToken)).build();

    }
}
