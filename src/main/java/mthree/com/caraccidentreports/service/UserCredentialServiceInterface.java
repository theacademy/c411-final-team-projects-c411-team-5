package mthree.com.caraccidentreports.service;

import mthree.com.caraccidentreports.model.UserCredential;

public interface UserCredentialServiceInterface {
    UserCredential addNewUserCredential(UserCredential userCredential);
    UserCredential getUserCredentialByUsername(String username);
    UserCredential updateUserCredential(String username, UserCredential userCredential);
    void deleteUserCredential(String username);
    public UserCredential verifyUserCredentials(UserCredential userCredential);
}
