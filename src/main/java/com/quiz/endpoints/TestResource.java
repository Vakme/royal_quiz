package com.quiz.endpoints;

import com.quiz.db.Test;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

import static com.quiz.db.DbConfig.getEntityManager;

@Path("/test")
@Produces("application/json")
public class TestResource implements ResourceBaseInterface<Test> {

    @PersistenceContext(type = PersistenceContextType.EXTENDED, unitName = "PostgresDS")
    private EntityManager em;

    private List<Test> results;

    @Override
    public List<Test> getList() {
        em = getEntityManager();
        em.getTransaction().begin();
        results = em.createNamedQuery(Test.FIND_ALL, Test.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return results;
    }

    @Override
    public List<Test> getSingle(int id) {
        em = getEntityManager();
        em.getTransaction().begin();
        results = Collections.singletonList(em.find(Test.class, id));
        em.getTransaction().commit();
        em.close();
        return results;
    }

    @Override
    public Response insertSingle(Test test) {
        em = getEntityManager();
        em.getTransaction().begin();
        em.persist(test);
        em.getTransaction().commit();
        em.close();
        return Response.ok().build();
    }
}
