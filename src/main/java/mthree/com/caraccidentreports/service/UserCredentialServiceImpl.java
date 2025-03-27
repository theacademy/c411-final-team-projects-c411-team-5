package mthree.com.caraccidentreports.service;

import mthree.com.caraccidentreports.dao.UserCredentialDao;
import mthree.com.caraccidentreports.model.Customer;
import mthree.com.caraccidentreports.model.UserCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialServiceImpl implements UserCredentialServiceInterface {
    private final UserCredentialDao userCredentialDao;

    @Autowired
    public UserCredentialServiceImpl(UserCredentialDao userCredentialDao) {
        this.userCredentialDao = userCredentialDao;
    }

    @Override
    public UserCredential addNewUserCredential(UserCredential userCredential) throws InvalidUsernameException, InvalidPasswordException {
        // Validate credentials before adding
        validateCreateUserCredentials(userCredential);
        if(getUserCredentialByUsername(userCredential.getUsername()) != null){
            throw new InvalidUsernameException("Username already exist");
        }
        // Save to database if validation is successful
        return userCredentialDao.addUserCredential(userCredential);
    }

    @Override
    public UserCredential getUserCredentialByUsername(String username) {
        UserCredential userCredential;

        try {
            userCredential = userCredentialDao.getUserCredentialByUsername(username);
        } catch (DataAccessException e) {
            userCredential = new UserCredential();
            userCredential.setUsername("User NOT Found");
            userCredential.setPassword("User NOT Found");
            userCredential.setEmail("User NOT Found");
        }

        return userCredential;
    }

    @Override
    public UserCredential verifyUserCredentials(UserCredential userCredential) {
        userCredential = userCredentialDao.checkUserCredentialsMatch(userCredential);
        if(userCredential == null){
            throw new CredentialsNotMatchException("Username and password don't match");
        }
        return userCredential;
    }

    @Override
    public UserCredential updateUserCredential(String username, UserCredential userCredential) {
        if (!username.equals(userCredential.getUsername())) {
            userCredential.setUsername("Usernames do not match, user credential not updated");
            userCredential.setPassword("Usernames do not match, user credential not updated");
            userCredential.setEmail("Usernames do not match, user credential not updated");
        } else {
            userCredentialDao.updateUserCredential(userCredential);
        }
        return userCredentialDao.getUserCredentialByUsername(username);
    }

    @Override
    public void deleteUserCredential(String username) {
        try {
            userCredentialDao.deleteUserCredential(username);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete user credential", e);
        }
    }

    public boolean validateCreateUserCredentials(UserCredential userCredential) throws InvalidUsernameException{
        if(userCredential.getUsername().length() > 255){
            throw new InvalidUsernameException("Username length can't be greater than 255 characters");
        }else if (userCredential.getUsername() == null || userCredential.getUsername().isBlank()) {
            throw new InvalidUsernameException("Username cannot be blank");
        } else if (userCredential.getPassword().length() > 255) {
            throw new InvalidPasswordException("Password length can't be greater than 255 characters");
        }
        else if(userCredential.getPassword().length() < 8){
            throw  new InvalidPasswordException("Password must have a length of at least 8 characters");
        } else if (!userCredential.getPassword().matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            throw new InvalidPasswordException("Password must contain at least one special character");
        }
        return true;
    }


}
