package mthree.com.caraccidentreports.service;

import mthree.com.caraccidentreports.dao.UserCredentialDao;
import mthree.com.caraccidentreports.model.Customer;
import mthree.com.caraccidentreports.model.UserCredential;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserCredentialServiceImplTests {
    private UserCredentialServiceImpl userCredentialService;

    @BeforeEach
    public void setUp() {
        UserCredentialDao userCredentialDao = new UserCredentialDaoStubImpl();
        userCredentialService = new UserCredentialServiceImpl(userCredentialDao);
    }

    @Test
    @DisplayName("Add User Credential Service Test")
    public void addUserCredentialServiceTest() {
        UserCredential userCredential = new UserCredential();
        userCredential.setUsername("user22");
        userCredential.setPassword("password123!");

        UserCredential newUserCredential = userCredentialService.addNewUserCredential(userCredential);
        assertNotNull(newUserCredential);
        assertEquals("user22", newUserCredential.getUsername());
        assertEquals("password123!", newUserCredential.getPassword());
    }

    @Test
    @DisplayName("Add User Credential With Blank Username Service Test")
    public void addUserCredentialWithBlankUsernameServiceTest() {
        UserCredential userCredential = new UserCredential();
        userCredential.setUsername("");
        userCredential.setPassword("password123!");

        // Assert that InvalidUsernameException is thrown
        InvalidUsernameException exception = assertThrows(
                InvalidUsernameException.class,
                () -> userCredentialService.addNewUserCredential(userCredential),
                "Expected addNewUserCredential() to throw, but it didn't"
        );

        // Verify the exception message
        assertEquals("Username cannot be blank", exception.getMessage());
    }

    @Test
    @DisplayName("Get User Credential By Username Service Test")
    public void getUserCredentialByUsernameServiceTest() {
        UserCredential userCredential = userCredentialService.getUserCredentialByUsername("user21");
        assertNotNull(userCredential);
        assertEquals("user21", userCredential.getUsername());
        assertEquals("password123", userCredential.getPassword());
    }

    @Test
    @DisplayName("Get User Credential By Non-Existent Username Service Test")
    public void getUserCredentialByNonExistentUsernameServiceTest() {
        UserCredential userCredential = userCredentialService.getUserCredentialByUsername("non_existent_user");
        assertNotNull(userCredential);
        assertEquals("User NOT Found", userCredential.getUsername());
        assertEquals("User NOT Found", userCredential.getPassword());
    }

    @Test
    @DisplayName("Update User Credential Service Test")
    public void updateUserCredentialServiceTest() {
        UserCredential userCredential = new UserCredential();
        userCredential.setUsername("user21");
        userCredential.setPassword("newPassword123");
        userCredential.setEmail("user21new@gmail.com");

        UserCredential updatedCredential = userCredentialService.updateUserCredential("user21", userCredential);

        assertNotNull(updatedCredential);
        assertEquals("user21", updatedCredential.getUsername());
        assertEquals("newPassword123", updatedCredential.getPassword());
        assertEquals("user21new@gmail.com", updatedCredential.getEmail());
    }

    @Test
    @DisplayName("User Credential No Update Service Test")
    public void userCredentialNoUpdateServiceTest() {
        UserCredential userCredential = new UserCredential();
        userCredential.setUsername("user21");
        userCredential.setPassword("newPassword123");
        userCredential.setEmail("user21new@gmail.com");

        UserCredential updatedCredential = userCredentialService.updateUserCredential("user20", userCredential);

        assertNotNull(updatedCredential);
        assertEquals("User NOT Found", updatedCredential.getPassword());
        assertEquals("User NOT Found", updatedCredential.getEmail());
    }

    @Test
    @DisplayName("Delete User Credential Service Test")
    public void deleteUserCredentialServiceTest() {
        String usernameToDelete = "user21";

        UserCredential userBeforeDelete = userCredentialService.getUserCredentialByUsername(usernameToDelete);
        assertNotNull(userBeforeDelete);
        assertEquals(usernameToDelete, userBeforeDelete.getUsername());

        userCredentialService.deleteUserCredential(usernameToDelete);
        UserCredential userAfterDelete = userCredentialService.getUserCredentialByUsername(usernameToDelete);
        assertNotNull(userAfterDelete);
        assertEquals("User NOT Found", userAfterDelete.getUsername());
        assertEquals("User NOT Found", userAfterDelete.getPassword());
    }
}