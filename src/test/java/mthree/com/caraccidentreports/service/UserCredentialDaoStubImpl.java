package mthree.com.caraccidentreports.service;

import mthree.com.caraccidentreports.dao.UserCredentialDao;
import mthree.com.caraccidentreports.model.UserCredential;
import org.h2.engine.User;

import java.util.List;

public class UserCredentialDaoStubImpl implements UserCredentialDao {

    public UserCredential userCredential;

    public UserCredentialDaoStubImpl() {
        userCredential = new UserCredential();
        userCredential.setUsername("user21");
        userCredential.setPassword("password123");
        userCredential.setEmail("user21@gmail.com");
    }

    @Override
    public UserCredential addUserCredential(UserCredential userCredential) {
        if (userCredential.getUsername() == null || userCredential.getUsername().trim().isEmpty()) {
            userCredential.setUsername("Username blank, password NOT added");
        }
        return userCredential;
    }

    @Override
    public List<UserCredential> getAllUserCredentials() {
        return List.of(userCredential);
    }

    @Override
    public UserCredential getUserCredentialByUsername(String username) {
        if ("user21".equals(username)) {
            return userCredential;
        }
        else if("user22".equals(username)){
            return null;
        }
        else {
            UserCredential userCredential = new UserCredential();
            userCredential.setUsername("User NOT Found");
            userCredential.setPassword("User NOT Found");
            userCredential.setEmail("User NOT Found");
            return userCredential;
        }
    }

    @Override
    public void updateUserCredential(UserCredential userCredential) {
        this.userCredential.setUsername(userCredential.getUsername());
        this.userCredential.setPassword(userCredential.getPassword());
        this.userCredential.setEmail(userCredential.getEmail());
    }

    @Override
    public void deleteUserCredential(String username) {
        if (userCredential.getUsername() == username) {
            userCredential.setUsername("User NOT Found");
            userCredential.setPassword("User NOT Found");
            userCredential.setEmail("User NOT Found");
        }
    }

    @Override
    public UserCredential checkUserCredentialsMatch(UserCredential userCredential) {
        return null;
    }
}