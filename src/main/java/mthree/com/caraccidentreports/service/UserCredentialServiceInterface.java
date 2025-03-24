package mthree.com.caraccidentreports.service;

import mthree.com.caraccidentreports.model.UserCredential;

public interface UserCredentialServiceInterface {
    UserCredential getUserCredentialByUsername(String username);
    UserCredential addNewUserCredential(UserCredential userCredential);
}
