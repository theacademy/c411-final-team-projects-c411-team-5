package mthree.com.caraccidentreports.sql;

import mthree.com.caraccidentreports.App;
import mthree.com.caraccidentreports.dao.mappers.CustomerMapper;
import mthree.com.caraccidentreports.dao.mappers.UserCredentialMapper;
import mthree.com.caraccidentreports.model.Customer;
import mthree.com.caraccidentreports.model.UserCredential;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootTest(classes = App.class)
public class DatabaseInitTest {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseInitTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Test
    @DisplayName("Does customers table exist and filled correctly")
    public void dbExist() {
        final String sql = "SELECT * FROM customer";
        List<Customer> customerList = jdbcTemplate.query(sql, new CustomerMapper());
        Assertions.assertFalse(customerList.isEmpty());
        Assertions.assertEquals(20, customerList.size());
    }

    @Test
    @DisplayName("Does credentials table exist and filled correctly")
    public void dbExist2() {
        final String sql = "SELECT * FROM user_cred";
        List<UserCredential> credentials = jdbcTemplate.query(sql, new UserCredentialMapper());
        Assertions.assertFalse(credentials.isEmpty());
        Assertions.assertEquals(20, credentials.size());
    }

    @Test
    @DisplayName("Does customer exists")
    public void testCustomerExist(){
        final String sql = "SELECT * FROM customer WHERE cid = ?";
        Customer customer = jdbcTemplate.queryForObject(sql, new CustomerMapper(), 5);
        Assertions.assertNotNull(customer);
        Assertions.assertEquals("Charlie",customer.getfName());
        Assertions.assertEquals("Davis", customer.getlName());
        Assertions.assertEquals("user5", customer.getUsername());
        Assertions.assertEquals(5, customer.getCid());
    }

    @Test
    @DisplayName("Does customer not exists")
    public void testCustomerNotExist(){
        final String sql = "SELECT * FROM customer WHERE cid = ?;";
        List<Customer> customer = jdbcTemplate.query(sql, new CustomerMapper(), 100);
        Assertions.assertEquals(0, customer.size());
    }

    @Test
    @DisplayName("Does all user data is retrieved")
    public void testGetAllCustomerData(){
        String sql = "SELECT * FROM customer WHERE username = ?;";
        Customer customer = jdbcTemplate.queryForObject(sql, new CustomerMapper(), "user5");
        sql = "SELECT * FROM user_cred WHERE username = ?;";
        UserCredential credential = jdbcTemplate.queryForObject(sql, new UserCredentialMapper(), "user5");
        Assertions.assertEquals(customer.getUsername(), credential.getUsername());
        Assertions.assertEquals("password5",credential.getPassword());
        Assertions.assertEquals("Charlie",customer.getfName());
        Assertions.assertEquals("Davis", customer.getlName());
        Assertions.assertEquals("user5", customer.getUsername());
        Assertions.assertEquals(5, customer.getCid());
    }
}
