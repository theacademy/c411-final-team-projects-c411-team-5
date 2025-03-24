package mthree.com.caraccidentreports.service;

import mthree.com.caraccidentreports.dao.UserCredentialDao;
import mthree.com.caraccidentreports.model.UserCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialServiceImpl implements UserCredentialServiceInterface {
    private final UserCredentialDao userCredentialDao;

    @Autowired
    public UserCredentialServiceImpl(UserCredentialDao userCredentialDao) {
        this.userCredentialDao = userCredentialDao;
    }

    @Override
    public UserCredential getUserCredentialByUsername(String username) {
        UserCredential userCredential;

        try {
            userCredential = userCredentialDao.getUserCredentialByUsername(username);
        } catch (Exception e) {
            userCredential = new UserCredential();
            userCredential.setUsername("User Not Found");
            userCredential.setPassword("User Not Found");
        }

        return userCredential;
    }

    @Override
    public UserCredential addNewUserCredential(UserCredential userCredential) {
        if (userCredential.getUsername() == null || userCredential.getUsername().trim().isEmpty()) {
            userCredential.setUsername("Invalid Username");
        }

        userCredentialDao.addUserCredential(userCredential);
        return userCredential;
    }
}
