package mthree.com.caraccidentreports.model;

import java.util.Objects;

public class UserCredential {
    private String username;
    private String password;
    private String email;
    private String city;

    public String getUsername() {return username;}

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {return city;}

    public void setCity(String city) {this.city = city;}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserCredential that = (UserCredential) o;
        return Objects.equals(getUsername(), that.getUsername())
                && Objects.equals(getPassword(), that.getPassword())
                && Objects.equals(getEmail(), that.getEmail())
                && Objects.equals(getCity(), that.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), getEmail(), getCity());
    }
}
