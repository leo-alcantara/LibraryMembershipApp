package com.thebug.library_system.data;

import com.thebug.library_system.model.BookLoan;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Collection;

@Repository
public class BookLoanDAORepository implements BookLoanDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public BookLoan findById(int id) {
        return entityManager.find(BookLoan.class, id);
    }

    @Override
    @Transactional
    public Collection<BookLoan> findAll() {
        Query query = entityManager.createQuery("SELECT b FROM BookLoan b", BookLoan.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public BookLoan create(BookLoan bookLoan) {
        entityManager.persist(bookLoan);
        return bookLoan;
    }

    @Override
    @Transactional
    public BookLoan update(BookLoan bookLoan) {
        return entityManager.merge(bookLoan);
    }

    @Override
    @Transactional
    public void delete(int id) {
        entityManager.remove(id);

    }
}
