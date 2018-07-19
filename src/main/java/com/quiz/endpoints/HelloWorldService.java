package com.quiz.endpoints;

import com.quiz.annotations.RestrictedContentAnnotation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/HelloWorld")
public class HelloWorldService {

    @GET
    @Path("/sayHello")
    @RestrictedContentAnnotation
    public String sayHello() {
        return "<h1>Hello World</h1>";
    }

}