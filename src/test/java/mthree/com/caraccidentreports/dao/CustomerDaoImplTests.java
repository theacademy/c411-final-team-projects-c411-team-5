package mthree.com.caraccidentreports.dao;

import mthree.com.caraccidentreports.model.Customer;
import mthree.com.caraccidentreports.model.UserCredential;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerDaoImplTests {

    private JdbcTemplate jdbcTemplate;
    private CustomerDao customerDao;
    private UserCredentialDao userCredentialDao;

    @Autowired
    public void CustomerDaoImplTests(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.customerDao = new CustomerDaoImpl(jdbcTemplate);
        this.userCredentialDao = new UserCredentialDaoImpl(jdbcTemplate);
    }

    @Test
    @DisplayName("Add New Customer Test")
    public void addNewCustomerTest() {
        UserCredential userCredential = new UserCredential();
        userCredential.setUsername("user21");
        userCredential.setPassword("password21");
        userCredentialDao.addUserCredential(userCredential);
        Customer customer = new Customer();
        customer.setUsername("user21");
        customer.setfName("William");
        customer.setlName("Gates");
        customer.setLid("4");
        customerDao.addCustomer(customer);
        List<Customer> customerList = customerDao.getAllCustomers();
        assertNotNull(customerList);
        assertEquals(21, customerList.size());
    }

    @Test
    @DisplayName("Get List of All Customers Test")
    public void getListOfAllCustomersTest() {
        List<Customer> customerList = customerDao.getAllCustomers();
        assertNotNull(customerList);
        assertEquals(20, customerList.size());
    }

    @Test
    @DisplayName("Find Customer By ID")
    public void findCustomerByIdTest() {
        Customer customer = customerDao.getCustomerById(1);
        assertNotNull(customer);
        assertEquals("user1", customer.getUsername());
        assertEquals("John", customer.getfName());
        assertEquals("Doe", customer.getlName());
    }

    @Test
    @DisplayName("Update Customer Info Test")
    public void updateCustomerInfoTest() {
        Customer customer = new Customer();
        customer.setCid(1);
        customer.setfName("William");
        customer.setlName("Gates");
        customerDao.updateCustomer(customer);
        List<Customer> customerList = customerDao.getAllCustomers();
        assertNotNull(customerList);
        int i = 0;
        for (Customer c : customerList) {
            if (c.getfName().contains("William")) {
                i++;
            }
        }
        assertTrue(i != 0);
    }

    @Test
    @DisplayName("Delete Customer Test")
    @Transactional
    public void deleteCustomerTest() {
        customerDao.deleteCustomer(5);
        assertNotNull(customerDao.getAllCustomers());
        assertEquals(19, customerDao.getAllCustomers().size());
    }
}