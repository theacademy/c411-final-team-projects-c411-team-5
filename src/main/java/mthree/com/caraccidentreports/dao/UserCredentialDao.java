package mthree.com.caraccidentreports.dao;

import mthree.com.caraccidentreports.model.Customer;
import mthree.com.caraccidentreports.model.UserCredential;

import java.util.List;

public interface UserCredentialDao {
    UserCredential addUserCredential(UserCredential userCredential);
    List<UserCredential> getAllUserCredentials();
    UserCredential getUserCredentialByUsername(String username);
    void updateUserCredential(UserCredential userCredential);
    void deleteUserCredential(String username);

    UserCredential checkUserCredentialsMatch(UserCredential userCredential);
}
