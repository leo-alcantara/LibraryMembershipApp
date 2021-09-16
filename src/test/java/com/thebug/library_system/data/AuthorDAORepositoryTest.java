package com.thebug.library_system.data;

import com.thebug.library_system.model.AppUser;
import com.thebug.library_system.model.Author;
import com.thebug.library_system.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
@Transactional
class AuthorDAORepositoryTest {

    @Autowired
    AuthorDAO authorDAO;
    @Autowired
    TestEntityManager testEntityManager;

    private Author testAuthor;
    private int testId;
    private Set<Book> books;

    @BeforeEach
    void setUp() {
        testAuthor = testEntityManager.persist(new Author("Leo", "Alcantara", books));
        testId = testAuthor.getAuthorId();

    }

    @Test
    void findById() {
        //Arrange
        Author foundById = null;
        //Act
        foundById = authorDAO.findById(testId);

        //Assert
        assertNotNull(foundById);
        assertEquals(foundById.getFirstName(), testAuthor.getFirstName());
    }

    @Test
    void findAll() {
        //Arrange
        Author author = new Author("Test", "tttt", null);
        authorDAO.create(author);
        Collection<Author> found;

        //Act
        found = authorDAO.findAll();

        //Assert
        assertEquals(2, found.size());
    }

    @Test
    void create() {
        //Arrange
        Author author = new Author("Test", "tttt", null);
        assertEquals(0, author.getAuthorId());

        //Act
        authorDAO.create(author);

        //Assert
        assertTrue(author.getAuthorId() != 0);
    }

    @Test
    void update() {
       //Arrange
        Author toUpdate = new Author("Dobbie", "Well", null);

        //Act
        authorDAO.create(toUpdate);
        Author updated = authorDAO.update(toUpdate);

        //Assert
        assertEquals(toUpdate.getAuthorId(), updated.getAuthorId());
        assertEquals("Dobbie", updated.getFirstName());

    }

    @Test
    void delete() {
        //Arrange
        assertNotNull(testAuthor);

        //Act
        authorDAO.delete(testAuthor.getAuthorId());

        //Assert
        assertNull(testEntityManager.find(Author.class, testId));
    }

}