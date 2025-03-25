package mthree.com.caraccidentreports.dao;

import mthree.com.caraccidentreports.model.UserCredential;

public interface UserCredentialDao {
    UserCredential addUserCredential(UserCredential userCredential);
    UserCredential getUserCredentialByUsername(String username);
    void updateUserCredential(String username, String newPassword);
    void deleteUserCredential(String username);
}
