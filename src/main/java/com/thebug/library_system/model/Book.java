package com.thebug.library_system.model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    private String isbn;
    private String title;
    private int maxLoanDays;
    @ManyToMany(cascade = {CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH},
            fetch = FetchType.LAZY)
            @JoinTable(name = "books_and_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    Set<Author> authors;

    public Book() {
    }

    public Book(String isbn, String title, int maxLoanDays, Set<Author> authors) {
        this.isbn = isbn;
        this.title = title;
        this.maxLoanDays = maxLoanDays;
        this.authors = authors;
    }

    //CONVENIENCE METHODS
    public void addAuthor(Author author){
        if(!authors.contains(author)){
            authors.add(author);
        }
    }

    public void removeAuthor (Author author){
        if(authors.contains(author)){
            authors.remove(author);
        }
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMaxLoanDays() {
        return maxLoanDays;
    }

    public void setMaxLoanDays(int maxLoanDays) {
        this.maxLoanDays = maxLoanDays;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return getMaxLoanDays() == book.getMaxLoanDays() && Objects.equals(getIsbn(), book.getIsbn()) && Objects.equals(getTitle(), book.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIsbn(), getTitle(), getMaxLoanDays());
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", maxLoanDays=" + maxLoanDays +
                '}';
    }
}
