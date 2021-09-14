package com.thebug.library_system.data;

import com.thebug.library_system.model.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Collection;

@Repository
public class BookDAORepository implements BookDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Book findById(int id) {
        return entityManager.find(Book.class, id);
    }

    @Override
    @Transactional
    public Collection<Book> findAll() {
        Query query = entityManager.createQuery("SELECT b FROM Book b", Book.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Book create(Book book) {
        entityManager.persist(book);
        return book;
    }

    @Override
    @Transactional
    public Book update(Book book) {
        return entityManager.merge(book);
    }

    @Override
    @Transactional
    public void delete(int id) {
        entityManager.remove(id);

    }
}
