package com.thebug.library_system.data;

import com.thebug.library_system.model.Author;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Collection;

@Repository
public class AuthorDAORepository implements AuthorDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Author findById(int id) {
        return entityManager.find(Author.class, id);
    }

    @Override
    @Transactional
    public Collection<Author> findAll() {
        Query query = entityManager.createQuery("SELECT a FROM Author a", Author.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Author create(Author author) {
        entityManager.persist(author);
        return author;
    }

    @Override
    @Transactional
    public Author update(Author author) {
        return entityManager.merge(author);
    }

    @Override
    @Transactional
    public void delete(int id) {
        entityManager.remove(findById(id));

    }
}
