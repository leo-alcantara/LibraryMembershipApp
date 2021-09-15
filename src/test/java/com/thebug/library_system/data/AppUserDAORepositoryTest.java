package com.thebug.library_system.data;

import com.thebug.library_system.model.AppUser;
import com.thebug.library_system.model.Details;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
@Transactional
class AppUserDAORepositoryTest {

    @Autowired
    AppUserDAO appUserDAO;
    @Autowired
    TestEntityManager testEntityManager;

    private AppUser testAppUser;
    private int testId;
    Details details = new Details();

    @BeforeEach
    void setUp() {
        testAppUser = testEntityManager.persist(new AppUser("TestDummy", "1234", details));
        testId = testAppUser.getAppUserId();
    }

    @Test
    void testFindById() {
        //Arrange
        AppUser foundById = null;
        //Act
        foundById = appUserDAO.findById(testId);

        //Assert
        assertNotNull(foundById);
        assertEquals(foundById.getUsername(), testAppUser.getUsername());
    }

    @Test
    void testFindAll() {
        //Arrange
        AppUser appUser = new AppUser("Test", "tttt", null);
        appUserDAO.create(appUser);
        Collection<AppUser> found;

        //Act
        found = appUserDAO.findAll();

        //Assert
        assertEquals(2, found.size());
    }

    @Test
    void testCreate() {
        //Arrange
        AppUser appUser = new AppUser("Test", "tttt", null);
        assertEquals(0, appUser.getAppUserId());

        //Act
        appUserDAO.create(appUser);

        //Assert
        assertTrue(appUser.getAppUserId() != 0);
    }

    @Test
    void testUpdate() {
        AppUser appUser = new AppUser("Test", "tttt", null);

        String username = "Yo";
        String password = "oooo";
        assertEquals(0, appUser.getAppUserId());

        //Act
        //appUserDAO.create(appUser);
        appUser.setPassword(username);
        appUser.setPassword(password);

        appUserDAO.update(appUser);

        //Assert
        assertTrue(appUser.getAppUserId() != 0);
        assertEquals(username, appUser.getUsername());
    }

    @Test
    void testDelete() {
        //Arrange
        assertNotNull(testAppUser);

        //Act
        appUserDAO.delete(testAppUser.getAppUserId());

        //Assert
       assertNull(testEntityManager.find(AppUser.class, testId));
    }
}