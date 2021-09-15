package com.thebug.library_system.data;

import com.thebug.library_system.model.AppUser;
import com.thebug.library_system.model.Author;
import com.thebug.library_system.model.Book;
import com.thebug.library_system.model.BookLoan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
@Transactional
class BookLoanDAORepositoryTest {

    @Autowired
    BookLoanDAO bookLoanDAO;
    @Autowired
    TestEntityManager testEntityManager;

    private BookLoan testBookLoan;
    private int testId;
    private Set<Book> books;
    private Book testBook;
    private AppUser testBorrower;

    @BeforeEach
    void setUp() {
        testBookLoan = testEntityManager.persist(new BookLoan(null, testBorrower, testBook));
        testId = testBookLoan.getLoanId();
    }

    @Test
    void findById() {
        //Arrange
        BookLoan foundById = null;
        //Act
        foundById = bookLoanDAO.findById(testId);

        //Assert
        assertNotNull(foundById);
        assertEquals(foundById.getLoanId(), testBookLoan.getLoanId());
    }

    @Test
    void findAll() {
        //Arrange
        BookLoan bookLoan = new BookLoan(null, testBorrower, testBook);
        bookLoanDAO.create(bookLoan);
        Collection<BookLoan> found;

        //Act
        found = bookLoanDAO.findAll();

        //Assert
        assertEquals(2, found.size());
    }

    @Test
    void create() {
        //Arrange
        BookLoan bookLoan = new BookLoan(null, testBorrower, testBook);
        assertEquals(0, bookLoan.getLoanId());

        //Act
        bookLoanDAO.create(bookLoan);

        //Assert
        assertTrue(bookLoan.getLoanId() != 0);
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
        //Arrange
        assertNotNull(testBookLoan);

        //Act
        bookLoanDAO.delete(testBookLoan.getLoanId());

        //Assert
        assertNull(testEntityManager.find(BookLoan.class, testId));
    }
}