package com.quiz.endpoints;

import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Produces("application/json")
public interface ResourceBaseInterface<T> {

    @GET
    public List<T> getList();

    @GET
    @Path("{id}")
    public List<T> getSingle(@PathParam("id") int id);

    @POST
    public Response insertSingle(T t);

}