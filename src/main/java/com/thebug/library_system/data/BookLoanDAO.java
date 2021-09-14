package com.thebug.library_system.data;

import com.thebug.library_system.model.BookLoan;

import java.util.Collection;

public interface BookLoanDAO {

    BookLoan findById(int id);
    Collection<BookLoan> findAll();
    BookLoan create(BookLoan bookLoan);
    BookLoan update(BookLoan bookLoan);
    void delete(int id);

}
