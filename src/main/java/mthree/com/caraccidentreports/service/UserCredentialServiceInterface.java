package mthree.com.caraccidentreports.service;

import mthree.com.caraccidentreports.model.UserCredential;

public interface UserCredentialServiceInterface {
    UserCredential addNewUserCredential(UserCredential userCredential);
    UserCredential getUserCredentialByUsername(String username);
    void updateUserPassword(String username, String newPassword);
    void deleteUserCredential(String username);
}
