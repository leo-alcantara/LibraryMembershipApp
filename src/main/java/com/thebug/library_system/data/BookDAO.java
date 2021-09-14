package com.thebug.library_system.data;

import com.thebug.library_system.model.Book;

import java.util.Collection;

public interface BookDAO {

    Book findById(int id);
    Collection<Book> findAll();
    Book create(Book book);
    Book update(Book book);
    void delete(int id);
}
