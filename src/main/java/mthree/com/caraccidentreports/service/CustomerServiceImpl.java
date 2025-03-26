package mthree.com.caraccidentreports.service;

import mthree.com.caraccidentreports.dao.CustomerDao;
import mthree.com.caraccidentreports.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerServiceInterface {
    private final CustomerDao customerDao;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public Customer addNewCustomer(Customer customer) {
        if (customer.getUsername() == null || customer.getUsername().trim().isEmpty()) {
            customer.setUsername("Username blank, customer NOT added");
            customer.setfName("Username blank, customer NOT added");
            customer.setlName("Username blank, customer NOT added");
        }

        return customerDao.addCustomer(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    @Override
    public Customer getCustomerById(int cid) {
        Customer customer;

        try {
            customer = customerDao.getCustomerById(cid);
        } catch (DataAccessException e) {
            customer = new Customer();
            customer.setUsername("Customer Not Found");
            customer.setfName("Customer Not Found");
            customer.setlName("Customer Not Found");
        }

        return customer;
    }

    @Override
    public Customer updateCustomer(int cid, Customer customer) {
        if (cid != customer.getCid()) {
            customer.setUsername("IDs do not match, customer not updated");
            customer.setfName("IDs do not match, customer not updated");
            customer.setlName("IDs do not match, customer not updated");
        } else {
            customerDao.updateCustomer(customer);
        }

        return customer;
    }

    @Override
    public void deleteCustomer(int cid) {
        try {
            customerDao.deleteCustomer(cid);
            System.out.println("Customer ID: " + cid + " deleted");
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete customer", e);
        }
    }

    public Customer getCustomerByUsername(String username) {
        Customer customer;

        try {
            customer = customerDao.getCustomerByUsername(username);
        } catch (DataAccessException e) {
            customer = new Customer();
            customer.setUsername("Customer Not Found");
            customer.setfName("Customer Not Found");
            customer.setlName("Customer Not Found");
        }

        return customer;
    }
}