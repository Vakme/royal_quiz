package com.quiz.annotations;

import javax.ws.rs.NameBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to indicate resources accessible only for logged in users.
 * Used with REST endpoints.
 * Every request to this endpoint with this annotation will be filtered
 * by RestrictedOperationRequestFilter.
 */
@NameBinding
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RestrictedContentAnnotation {}
