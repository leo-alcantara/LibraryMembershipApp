package com.thebug.library_system.data;

import com.thebug.library_system.model.BookLoan;
import com.thebug.library_system.model.Details;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import javax.xml.soap.Detail;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
@Transactional
class DetailsDAORepositoryTest {

    @Autowired
    DetailsDAO detailsDAO;
    @Autowired
    TestEntityManager testEntityManager;

    private Details testDetails;
    private int testId;

    @BeforeEach
    void setUp() {
        testDetails = testEntityManager.persist(new Details("malalal", "kjlaksjd", null));
        testId = testDetails.getDetailId();
    }

    @Test
    void findById() {
        //Arrange
        Details foundById = null;
        //Act
        foundById = detailsDAO.findById(testId);

        //Assert
        assertNotNull(foundById);
        assertEquals(foundById.getDetailId(), testDetails.getDetailId());
    }

    @Test
    void findAll() {

        //Arrange
        Details details = new Details("jkhkjg", "hjgjhg", null);
        detailsDAO.create(details);
        Collection<Details> found;

        //Act
        found = detailsDAO.findAll();

        //Assert
        assertEquals(2, found.size());
    }

    @Test
    void create() {

        Details details = new Details("jkhkjg", "hjgjhg", null);
        assertEquals(0, details.getDetailId());

        //Act
        detailsDAO.create(details);

        //Assert
        assertTrue(details.getDetailId() != 0);
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
        //Arrange
        assertNotNull(testDetails);

        //Act
        detailsDAO.delete(testDetails.getDetailId());

        //Assert
        assertNull(testEntityManager.find(Details.class, testId));
    }
}