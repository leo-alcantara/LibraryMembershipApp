package com.thebug.library_system.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appUserId;

    @Column(unique = true)
    private String username;
    private String password;
    private LocalDate regDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "detailsId")
    private Details userDetails;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "borrower")
    private List<BookLoan> loans;

    public AppUser() {
    }

    public AppUser(String username, String password, Details userDetails) {
        this.username = username;
        this.password = password;
        this.userDetails = userDetails;
        this.regDate = LocalDate.now();
    }


    //CONVENIENCE METHODS
    public void addBookLoan(BookLoan bookLoan) {
        if (bookLoan.getBook().isAvailable()){
            bookLoan.setBorrower(this);
            bookLoan.getBook().setAvailable(false);
            if (!loans.contains(bookLoan)) {
                loans.add(bookLoan);
            }
        }else {
            System.out.println("Book is not available!");
        }
    }

    public void removeBook(BookLoan bookLoan) {
        if (loans.contains(bookLoan)) {
            bookLoan.setBorrower(null);
            loans.remove(bookLoan);
            bookLoan.getBook().setAvailable(true);
        }
    }

    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public Details getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(Details userDetails) {
        this.userDetails = userDetails;
    }

    public List<BookLoan> getLoans() {
        return loans;
    }

    public void setLoans(List<BookLoan> loans) {
        this.loans = loans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return Objects.equals(getUsername(), appUser.getUsername()) && Objects.equals(getPassword(), appUser.getPassword()) && Objects.equals(getRegDate(), appUser.getRegDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), getRegDate());
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "appUserId=" + appUserId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", regDate=" + regDate +
                ", userDetails=" + userDetails +
                '}';
    }
}
