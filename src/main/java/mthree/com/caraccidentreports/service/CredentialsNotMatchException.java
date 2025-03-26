package mthree.com.caraccidentreports.service;

public class CredentialsNotMatchException extends RuntimeException {
    public CredentialsNotMatchException(String message) {
        super(message);
    }
}
