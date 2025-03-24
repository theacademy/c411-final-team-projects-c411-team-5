package mthree.com.caraccidentreports.dao;

import mthree.com.caraccidentreports.model.UserCredential;

public interface UserCredentialDao {
    UserCredential getUserCredentialByUsername(String username);
    void addUserCredential(UserCredential userCredential);
}
