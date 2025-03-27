package mthree.com.caraccidentreports.dao;

import mthree.com.caraccidentreports.model.Customer;
import mthree.com.caraccidentreports.model.UserCredential;
import mthree.com.caraccidentreports.service.UserCredentialServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserCredentialDaoImplTests {

    private final JdbcTemplate jdbcTemplate;
    private final UserCredentialDao userCredentialDao;

    @Autowired
    public UserCredentialDaoImplTests(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.userCredentialDao = new UserCredentialDaoImpl(jdbcTemplate);
    }

    @BeforeEach
    public void setUp() {
        UserCredential userCredential = new UserCredential();
        userCredential.setUsername("user21");
        userCredential.setPassword("password123");
        userCredential.setEmail("user21@gmail.com");
        userCredentialDao.addUserCredential(userCredential);
    }

    @Test
    @DisplayName("Add User Credential Test")
    @Transactional
    public void addUserCredentialTest() {
        UserCredential retrievedCredential = userCredentialDao.getUserCredentialByUsername("user21");
        assertNotNull(retrievedCredential, "UserCredential should not be null.");
        assertEquals("password123", retrievedCredential.getPassword(), "The password should match.");
    }

    @Test
    @DisplayName("Get User Credential By Username Test")
    public void getUserCredentialByUsernameTest() {
        UserCredential retrievedCredential = userCredentialDao.getUserCredentialByUsername("user21");
        assertNotNull(retrievedCredential);
        assertEquals("password123", retrievedCredential.getPassword(), "The password should match.");
    }

    @Test
    @DisplayName("Update User Credential Test")
    public void updateUserCredentialTest() {
        UserCredential updatedUserCredential = new UserCredential();
        updatedUserCredential.setUsername("user21");
        updatedUserCredential.setPassword("newPassword123");
        updatedUserCredential.setEmail("user21new@gmail.com");

        userCredentialDao.updateUserCredential(updatedUserCredential);

        List<UserCredential> credentialList = userCredentialDao.getAllUserCredentials();
        assertNotNull(credentialList);

        boolean isUpdated = credentialList.stream()
                .anyMatch(cred -> cred.getUsername().equals("user21") &&
                        cred.getPassword().equals("newPassword123") &&  // Check hash if needed
                        "user21new@gmail.com".equals(cred.getEmail()));

        assertTrue(isUpdated, "UserCredential should be updated correctly.");
    }

    @Test
    @DisplayName("Delete User Credential Test")
    @Transactional
    public void deleteUserCredentialTest() {
        userCredentialDao.deleteUserCredential("user21");
        assertNotNull(userCredentialDao.getAllUserCredentials());
        assertEquals(20, userCredentialDao.getAllUserCredentials().size());
    }
}