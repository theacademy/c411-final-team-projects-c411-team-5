package mthree.com.caraccidentreports.dao;

import mthree.com.caraccidentreports.model.UserCredential;

public interface UserCredentialDao {
    UserCredential addUserPassword(UserCredential userCredential);
    UserCredential getUserPasswordByUsername(String username);
    void updateUserPassword(String username, String newPassword);
}
