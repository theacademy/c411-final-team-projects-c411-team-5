package mthree.com.caraccidentreports.service;

import mthree.com.caraccidentreports.dao.UserCredentialDao;
import mthree.com.caraccidentreports.model.UserCredential;

public class UserCredentialDaoStubImpl implements UserCredentialDao {

    public UserCredential userCredential;

    public UserCredentialDaoStubImpl() {
        userCredential = new UserCredential();
        userCredential.setUsername("username21");
        userCredential.setPassword("password123");
    }

    @Override
    public UserCredential addUserCredential(UserCredential userCredential) {
        if (userCredential.getUsername() == null || userCredential.getUsername().trim().isEmpty()) {
            userCredential.setUsername("Username blank, password NOT added");
        }
        return userCredential;
    }

    @Override
    public UserCredential getUserCredentialByUsername(String username) {
        if ("username21".equals(username)) {
            return userCredential;
        } else {
            UserCredential userCredential = new UserCredential();
            userCredential.setUsername("User NOT Found");
            userCredential.setPassword("User NOT Found");
            return userCredential;
        }
    }

    @Override
    public void updateUserCredential(String username, String newPassword) {
        if ("username21".equals(username)) {
            userCredential.setPassword(newPassword);
        }
    }

    @Override
    public void deleteUserCredential(String username) {
        if (userCredential.getUsername() == username) {
            userCredential.setUsername("User NOT Found");
            userCredential.setPassword("User NOT Found");
        }
    }
}