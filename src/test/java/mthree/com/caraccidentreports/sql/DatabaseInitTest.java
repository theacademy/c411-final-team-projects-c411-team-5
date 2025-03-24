package mthree.com.caraccidentreports.sql;

import mthree.com.caraccidentreports.App;
import mthree.com.caraccidentreports.dao.mappers.CustomerMapper;
import mthree.com.caraccidentreports.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootTest(classes = App.class)
public class DatabaseInitTest {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseInitTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Test
    @DisplayName("Does db exist")
    public void dbExist() {
        final String sql = "SELECT * FROM customer";
        List<Customer> customerList = jdbcTemplate.query(sql, new CustomerMapper());
        Assertions.assertFalse(customerList.isEmpty());
    }
}
