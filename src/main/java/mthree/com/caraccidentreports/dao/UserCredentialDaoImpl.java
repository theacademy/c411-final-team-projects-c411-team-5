package mthree.com.caraccidentreports.dao;

import mthree.com.caraccidentreports.dao.mappers.UserCredentialMapper;
import mthree.com.caraccidentreports.model.UserCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserCredentialDaoImpl implements UserCredentialDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserCredentialDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserCredential addUserCredential(UserCredential userCredential) {
        final String sql = "INSERT INTO user_cred (username, password, email) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, userCredential.getUsername(), userCredential.getPassword(),
                            userCredential.getEmail());

        return userCredential;
    }

    @Override
    public List<UserCredential> getAllUserCredentials() {
        final String sql = "SELECT * FROM user_cred";
        return jdbcTemplate.query(sql, new UserCredentialMapper());
    }

    @Override
    public UserCredential getUserCredentialByUsername(String username) {
        try {
            final String sql = "SELECT * FROM user_cred WHERE username = ?";
            return jdbcTemplate.queryForObject(sql, new UserCredentialMapper(), username);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public void updateUserCredential(UserCredential userCredential) {
        final String sql = "UPDATE user_cred SET "
                + "password = ?, "
                + "email = ? "
                + "WHERE username = ?;";

        jdbcTemplate.update(sql,
                userCredential.getPassword(),
                userCredential.getEmail(),
                userCredential.getUsername());
    }

    @Override
    public void deleteUserCredential(String username) {
        final String sql = "DELETE FROM user_cred WHERE username = ?";
        jdbcTemplate.update(sql, username);
    }

    public UserCredential checkUserCredentialsMatch(UserCredential userCredential){
        try {
            final String sql = "SELECT * FROM user_cred WHERE username = ? AND password = ?";
            return jdbcTemplate.queryForObject(sql, new UserCredentialMapper(), userCredential.getUsername(), userCredential.getPassword());
        } catch (DataAccessException e) {
            return null;
        }
    }
}