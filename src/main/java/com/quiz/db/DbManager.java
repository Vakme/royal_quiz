package com.quiz.db;

import javax.persistence.*;
import javax.persistence.criteria.*;

import java.util.List;
import java.util.Map;

import static com.quiz.db.DbConfig.getEntityManager;

public class DbManager<T> {

    private static enum OperationType {
        EQUAL,
        GREATER,
        LESSER
    }

    @PersistenceContext(type = PersistenceContextType.EXTENDED, unitName = "PostgresDS")
    private EntityManager em;

    public DbManager() {
        em = getEntityManager();
    }

    public void insertSingle(T object) throws IllegalStateException, RollbackException {
        em.getTransaction().begin();
        em.persist(object);
        em.getTransaction().commit();
        em.close();
    }

    public <U> List<T> findEqualByParam(Class<T> tClass, Map.Entry<String, Class<U>> paramName, U paramValue) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cbQuery = cb.createQuery(tClass);
        Root<T> c = cbQuery.from(tClass);
        ParameterExpression<U> pClass = cb.parameter(paramName.getValue());
        Predicate op = cb.equal(c.get(paramName.getKey()), pClass);
        cbQuery.select(c).where(op);

        TypedQuery<T> query = em.createQuery(cbQuery);
        query.setParameter(pClass, paramValue);
        return query.getResultList();
    }
}
