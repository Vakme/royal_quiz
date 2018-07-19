package com.quiz.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.quiz.annotations.RestrictedContentAnnotation;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Date;

@Provider
@RestrictedContentAnnotation
public class RestrictedOperationsRequestFilter implements ContainerRequestFilter {

    private boolean validate(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withSubject("authQuiz")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("VERIFIED token" + jwt.getToken());
        } catch (JWTVerificationException exception){
            System.out.println(exception.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public void filter(ContainerRequestContext ctx) {
        Cookie cookie = ctx.getCookies().get("authToken");
        if(!validate(cookie.getValue())) {
            ctx.abortWith(Response.status(Response.Status.FORBIDDEN)
                    .entity("Cannot access")
                    .build());
        }
    }
}