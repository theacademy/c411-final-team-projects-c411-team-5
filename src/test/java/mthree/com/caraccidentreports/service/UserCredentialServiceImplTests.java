package mthree.com.caraccidentreports.service;

import mthree.com.caraccidentreports.dao.UserCredentialDao;
import mthree.com.caraccidentreports.model.UserCredential;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserCredentialServiceImplTests {
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
        userCredential.setUsername("username22");
        userCredential.setPassword("password123!");

        UserCredential newUserCredential = userCredentialService.addNewUserCredential(userCredential);
        assertNotNull(newUserCredential);
        assertEquals("username22", newUserCredential.getUsername());
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
        UserCredential userCredential = userCredentialService.getUserCredentialByUsername("username21");
        assertNotNull(userCredential);
        assertEquals("username21", userCredential.getUsername());
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
    @DisplayName("Update User Password Service Test")
    public void updateUserPasswordServiceTest() {
        userCredentialService.updateUserPassword("username21", "newPassword123!");
        UserCredential updatedUserCredential = userCredentialService.getUserCredentialByUsername("username21");
        assertNotNull(updatedUserCredential);
        assertEquals("newPassword123!", updatedUserCredential.getPassword());
    }

    @Test
    @DisplayName("Delete User Credential Service Test")
    public void deleteUserCredentialServiceTest() {
        String usernameToDelete = "username21";

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