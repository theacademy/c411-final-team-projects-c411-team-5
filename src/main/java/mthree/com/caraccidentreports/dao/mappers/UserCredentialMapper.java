package mthree.com.caraccidentreports.dao.mappers;

import mthree.com.caraccidentreports.model.UserCredential;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCredentialMapper implements RowMapper<UserCredential> {
    @Override
    public UserCredential mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserCredential userCredential = new UserCredential();
        userCredential.setUsername(rs.getString("username"));
        userCredential.setPassword(rs.getString("password"));
        userCredential.setEmail(rs.getString("email"));
        return userCredential;
    }
}
