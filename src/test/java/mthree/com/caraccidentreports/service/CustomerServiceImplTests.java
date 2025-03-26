package mthree.com.caraccidentreports.service;

import mthree.com.caraccidentreports.dao.CustomerDao;
import mthree.com.caraccidentreports.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerServiceImplTests {

    private CustomerServiceImpl customerService;

    @BeforeEach
    public void setUp() {
        CustomerDao customerDao = new CustomerDaoStubImpl();
        customerService = new CustomerServiceImpl(customerDao);
    }


    @Test
    @DisplayName("Customer Add Service Test")
    public void customerAddServiceTest() {
        Customer customer = new Customer();
        customer.setfName("New First Name");
        customer.setlName("New Last Name");
        customer.setUsername("New Username");

        Customer newCustomer = customerService.addNewCustomer(customer);
        assertNotNull(newCustomer);
        assertEquals("New First Name", newCustomer.getfName());
        assertEquals("New Last Name", newCustomer.getlName());
        assertEquals("New Username", newCustomer.getUsername());
    }

    @Test
    @DisplayName("Customer No Add Service Test")
    public void customerNoAddServiceTest() {
        Customer customer = new Customer();
        customer.setUsername("");
        customer.setfName("");
        customer.setlName("");

        Customer newCustomer = customerService.addNewCustomer(customer);
        assertEquals("Username blank, customer NOT added", newCustomer.getUsername());
        assertEquals("Username blank, customer NOT added", newCustomer.getfName());
        assertEquals("Username blank, customer NOT added", newCustomer.getlName());
    }

    @Test
    @DisplayName("Find Customer Service Test")
    public void findCustomerServiceTest() {
        Customer returnCustomer = customerService.getCustomerById(21);
        assertNotNull(returnCustomer);
        assertEquals("William", returnCustomer.getfName());
        assertEquals("Gates", returnCustomer.getlName());
    }

    @Test
    @DisplayName("Customer Not Found Service Test")
    public void customerNotFoundServiceTest() {
        Customer notFound = customerService.getCustomerById(99);
        assertNotNull(notFound);
        assertEquals("Customer Not Found", notFound.getfName());
        assertEquals("Customer Not Found", notFound.getlName());
    }

    @Test
    @DisplayName("Update Customer Service Test")
    public void updateCustomerServiceTest() {
        Customer customer = new Customer();
        customer.setCid(100);
        customer.setfName("Updated First Name");
        customer.setlName("Updated Last Name");

        Customer updatedCustomer = customerService.updateCustomer(100, customer);
        assertEquals(100, customer.getCid());
        assertNotNull(updatedCustomer);
        assertEquals("Updated First Name", updatedCustomer.getfName());
        assertEquals("Updated Last Name", updatedCustomer.getlName());
    }

    @Test
    @DisplayName("Customer No Update Service Test")
    public void customerNoUpdateServiceTest() {
        Customer customer = new Customer();
        customer.setCid(100);
        customer.setfName("Updated First Name");
        customer.setlName("Updated Last Name");

        Customer updatedCustomer = customerService.updateCustomer(99, customer);
        assertEquals("IDs do not match, customer not updated", updatedCustomer.getfName());
        assertEquals("IDs do not match, customer not updated", updatedCustomer.getlName());
    }

    @Test
    @DisplayName("Delete Customer Service Test")
    public void deleteCustomerServiceTest() {
        int customerIdToDelete = 21;

        Customer customerBeforeDelete = customerService.getCustomerById(customerIdToDelete);
        assertNotNull(customerBeforeDelete);
        assertEquals(customerIdToDelete, customerBeforeDelete.getCid());

        customerService.deleteCustomer(customerIdToDelete);

        Customer customerAfterDelete = customerService.getCustomerById(customerIdToDelete);
        assertNotNull(customerAfterDelete);
        assertEquals("Customer Not Found", customerAfterDelete.getUsername());
        assertEquals("Customer Not Found", customerAfterDelete.getfName());
        assertEquals("Customer Not Found", customerAfterDelete.getlName());
    }
}