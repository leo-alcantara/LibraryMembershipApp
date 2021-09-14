package com.thebug.library_system.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class BookLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loanId;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private boolean returned;
    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "appUserId")
    private AppUser borrowed;
    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "bookId")
    private Book book;

    public BookLoan() {
    }

    public BookLoan(LocalDate dueDate, AppUser borrowed, Book book) {
        this.dueDate = dueDate;
        this.borrowed = borrowed;
        this.book = book;
        this.loanDate = LocalDate.now();
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public AppUser getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(AppUser borrowed) {
        this.borrowed = borrowed;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookLoan bookLoan = (BookLoan) o;
        return isReturned() == bookLoan.isReturned() && Objects.equals(getLoanDate(), bookLoan.getLoanDate()) && Objects.equals(getDueDate(), bookLoan.getDueDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLoanDate(), getDueDate(), isReturned());
    }

    @Override
    public String toString() {
        return "BookLoan{" +
                "loanId=" + loanId +
                ", loanDate=" + loanDate +
                ", dueDate=" + dueDate +
                ", returned=" + returned +
                ", borrowed=" + borrowed +
                ", book=" + book +
                '}';
    }
}
