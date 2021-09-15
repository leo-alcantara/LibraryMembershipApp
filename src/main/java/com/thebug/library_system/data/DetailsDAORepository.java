package com.thebug.library_system.data;

import com.thebug.library_system.model.Details;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Collection;

@Repository
public class DetailsDAORepository implements DetailsDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Details findById(int id) {
        Details details = entityManager.find(Details.class, id);
        return details;
    }

    @Override
    @Transactional
    public Collection<Details> findAll() {
        Query query = entityManager.createQuery("SELECT d FROM Details d");
        return query.getResultList();
    }

    @Override
    @Transactional
    public Details create(Details details) {
        entityManager.persist(details);
        return details;
    }

    @Override
    @Transactional
    public Details update(Details details) {
        return entityManager.merge(details);
    }

    @Override
    @Transactional
    public void delete(int id) {
        entityManager.remove(findById(id));

    }
}
