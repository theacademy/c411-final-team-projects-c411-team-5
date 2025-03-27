package mthree.com.caraccidentreports.dao;

import mthree.com.caraccidentreports.dao.CustomerDao;
import mthree.com.caraccidentreports.dao.mappers.CustomerMapper;
import mthree.com.caraccidentreports.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Customer addCustomer(Customer customer) {
        final String sql = "INSERT INTO customer (username, lid, fName, lName) VALUES (?, ?, ?, ?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, String.valueOf(customer.getUsername()));
            statement.setString(2, String.valueOf(customer.getLid()));
            statement.setString(3, String.valueOf(customer.getfName()));
            statement.setString(4, String.valueOf(customer.getlName()));

            return statement;
        }, keyHolder);

        customer.setCid(keyHolder.getKey().intValue());

        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        final String sql = "SELECT * FROM customer";
        return jdbcTemplate.query(sql, new CustomerMapper());
    }

    @Override
    public Customer getCustomerById(int cid) {
        final String sql = "SELECT * FROM customer WHERE cid = ?";
        return jdbcTemplate.queryForObject(sql, new CustomerMapper(), cid);
    }

    @Override
    public void updateCustomer(Customer customer) {
        final String sql = "UPDATE customer SET " +
                "username = ?, " +
                "fName = ?, " +
                "lName = ?, " +
                "lid = ? " +
                "WHERE cid = ?";

        jdbcTemplate.update(sql,
                customer.getUsername(),
                customer.getfName(),
                customer.getlName(),
                customer.getLid(),
                customer.getCid());
    }

    @Override
    public void deleteCustomer(int cid) {
        final String sql = "DELETE FROM customer WHERE cid = ?";
        jdbcTemplate.update(sql, cid);
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        final String sql = "SELECT * FROM customer WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, new CustomerMapper(), username);    }
}