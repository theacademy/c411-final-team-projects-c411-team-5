package mthree.com.caraccidentreports.dao;

import mthree.com.caraccidentreports.dao.mappers.CustomerDao;
import mthree.com.caraccidentreports.dao.mappers.CustomerMapper;
import mthree.com.caraccidentreports.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Customer> getAllCustomers() {
        String sql = "SELECT * FROM customer";
        return jdbcTemplate.query(sql, new CustomerMapper());
    }

    @Override
    public Customer getCustomerById(int cid) {
        Customer customer;
        String sql = "SELECT * FROM customer WHERE cid = ?";

        try {
            customer = jdbcTemplate.queryForObject(sql, new CustomerMapper(), cid);
        } catch (DataAccessException e) {
            customer = new Customer();
            customer.setUsername("Customer Not Found");
            customer.setfName("Customer Not Found");
            customer.setlName("Customer Not Found");
        }

        return customer;
    }

    @Override
    public void addCustomer(Customer customer) {
        if (customer.getUsername() == null || customer.getUsername().trim().isEmpty()) {
            return;
        }

        String sql = "INSERT INTO customer (cid, username, fName, lName) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, customer.getCid(), customer.getUsername(), customer.getfName(), customer.getlName());
    }

    @Override
    public void updateCustomer(Customer customer) {
        String sql = "UPDATE customer SET username = ?, fName = ?, lName = ? WHERE cid = ?";
        jdbcTemplate.update(sql, customer.getUsername(), customer.getfName(), customer.getlName(), customer.getCid());
    }

    @Override
    public void deleteCustomer(int cid) {
        String sql = "DELETE FROM customer WHERE cid = ?";
        jdbcTemplate.update(sql, cid);
        System.out.println("Customer ID: " + cid + " deleted");
    }
}