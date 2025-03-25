package mthree.com.caraccidentreports.dao;

import mthree.com.caraccidentreports.model.UserCredential;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserCredentialDaoImplTest {
    private JdbcTemplate jdbcTemplate;
    private UserCredentialDao userCredentialDao;

    @Autowired
    public void UserCredentialDaoImplTests(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.userCredentialDao = new UserCredentialDaoImpl(jdbcTemplate);
    }

    @BeforeEach
    public void setUp() {
        String username = "username21";
        String fName = "William";
        String lName = "Gates";
        String insertCustomerSQL = "INSERT INTO customer (username, fName, lName) VALUES (?, ?, ?)";
        jdbcTemplate.update(insertCustomerSQL, username, fName, lName);

        UserCredential userCredential = new UserCredential();
        userCredential.setUsername(username);
        userCredential.setPassword("password123");
        userCredentialDao.addUserCredential(userCredential);
    }

    @Test
    @DisplayName("Add User Credential Test")
    @Transactional
    public void addUserCredentialTest() {
        String username = "username21";
        UserCredential retrievedCredential = userCredentialDao.getUserCredentialByUsername(username);
        assertNotNull(retrievedCredential, "UserCredential should not be null.");
        assertNotNull(retrievedCredential.getPassword(), "User password should not be null.");
        assertEquals("password123", retrievedCredential.getPassword(), "The password should match.");
    }

    @Test
    @DisplayName("Get User Credential By Username Test")
    public void getUserCredentialByUsernameTest() {
        String username = "username21";
        UserCredential retrievedCredential = userCredentialDao.getUserCredentialByUsername(username);  // Replace with an actual username in your DB
        assertNotNull(retrievedCredential);
        assertEquals("password123", retrievedCredential.getPassword(), "The password should match.");
    }

    @Test
    @DisplayName("Update User Password Test")
    public void updateUserPasswordTest() {
        String username = "username21";
        String newPassword = "newPassword123";
        userCredentialDao.updateUserCredential(username, newPassword);
        UserCredential retrievedCredential = userCredentialDao.getUserCredentialByUsername(username);
        assertEquals(newPassword, retrievedCredential.getPassword(), "The password should match.");
    }
}