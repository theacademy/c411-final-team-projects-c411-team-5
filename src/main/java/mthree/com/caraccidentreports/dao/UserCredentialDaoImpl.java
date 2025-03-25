package mthree.com.caraccidentreports.dao;

import mthree.com.caraccidentreports.dao.mappers.UserCredentialMapper;
import mthree.com.caraccidentreports.model.UserCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserCredentialDaoImpl implements UserCredentialDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserCredentialDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserCredential addUserCredential(UserCredential userCredential) {
        final String sql = "INSERT INTO user_cred (username, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, userCredential.getUsername(), userCredential.getPassword());

        return userCredential;
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
    public void updateUserCredential(String username, String newPassword) {
        final String sql = "UPDATE user_cred SET password = ? WHERE username = ?";
        jdbcTemplate.update(sql, newPassword, username);
    }

    @Override
    public void deleteUserCredential(String username) {
        final String sql = "DELETE FROM user_cred WHERE username = ?";
        jdbcTemplate.update(sql, username);
    }
}