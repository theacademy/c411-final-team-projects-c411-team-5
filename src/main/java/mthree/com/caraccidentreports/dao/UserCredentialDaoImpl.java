package mthree.com.caraccidentreports.dao;

import mthree.com.caraccidentreports.dao.mappers.UserCredentialMapper;
import mthree.com.caraccidentreports.model.UserCredential;
import org.springframework.beans.factory.annotation.Autowired;
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
    public UserCredential addUserPassword(UserCredential userCredential) {
        final String sql = "INSERT INTO user_cred (password) VALUES (?)";

        jdbcTemplate.update(sql, userCredential.getPassword());

        return userCredential;
    }

    @Override
    public UserCredential getUserPasswordByUsername(String username) {
        final String sql = "SELECT * FROM user_cred WHERE username = ?";

        return jdbcTemplate.queryForObject(sql, new UserCredentialMapper(), username);
    }

    @Override
    public void updateUserPassword(String username, String newPassword) {
        final String sql = "UPDATE user_cred SET password = ? WHERE username = ?";

        jdbcTemplate.update(sql, newPassword, username);
    }
}