package com.thebug.library_system.data;

import com.thebug.library_system.model.AppUser;
import com.thebug.library_system.model.Author;
import com.thebug.library_system.model.Book;
import com.thebug.library_system.model.Details;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
@Transactional
class BookDAORepositoryTest {

    @Autowired
    BookDAO bookDAO;
    @Autowired
    TestEntityManager testEntityManager;

    private Book testBook;
    private int testId;
    Set<Author> authors = new HashSet<>();

    @BeforeEach
    void setUp() {
        testBook = testEntityManager.persist(new Book("SIOUSIU", "HIUMO", 10, authors));
        testId = testBook.getBookId();
    }

    @Test
    void findById() {
        //Arrange
        Book foundById = null;
        //Act
        foundById = bookDAO.findById(testId);

        //Assert
        assertNotNull(foundById);
        assertEquals(foundById.getTitle(), testBook.getTitle());
    }

    @Test
    void findAll() {
        //Arrange
        Book book = new Book("asjkhdka", "hjasgda", 15, null);
        bookDAO.create(book);
        Collection<Book> found;

        //Act
        found = bookDAO.findAll();

        //Assert
        assertEquals(2, found.size());
    }

    @Test
    void create() {
        //Arrange
        Book book = new Book("asjkhdka", "hjasgda", 15, null);
        assertEquals(0, book.getBookId());

        //Act
        bookDAO.create(book);

        //Assert
        assertTrue(book.getBookId() != 0);
    }

    @Test
    void update() {

    }

    @Test
    void delete() {
        //Arrange
        assertNotNull(testBook);

        //Act
        bookDAO.delete(testBook.getBookId());

        //Assert
        assertNull(testEntityManager.find(Book.class, testId));
    }
}