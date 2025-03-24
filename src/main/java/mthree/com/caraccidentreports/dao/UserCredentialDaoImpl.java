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
    public UserCredential getUserCredentialByUsername(String username) {
        UserCredential userCredential;
        String sql = "SELECT * FROM user_cred WHERE username = ?";

        try {
            userCredential = jdbcTemplate.queryForObject(sql, new UserCredentialMapper(), username);
        } catch (DataAccessException e) {
            userCredential = new UserCredential();
            userCredential.setUsername("User Not Found");
            userCredential.setPassword("User Not Found");
        }

        return userCredential;
    }

    @Override
    public void addUserCredential(UserCredential userCredential) {
        if (userCredential.getUsername() == null || userCredential.getUsername().trim().isEmpty()) {
            return;
        }

        String sql = "INSERT INTO user_cred (username, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, userCredential.getUsername(), userCredential.getPassword());
    }
}