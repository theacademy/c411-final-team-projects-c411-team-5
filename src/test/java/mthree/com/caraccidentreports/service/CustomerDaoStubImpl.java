package mthree.com.caraccidentreports.service;

import mthree.com.caraccidentreports.dao.CustomerDao;
import mthree.com.caraccidentreports.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerDaoStubImpl implements CustomerDao {

    public Customer customer;

    public CustomerDaoStubImpl() {
        customer = new Customer();
        customer.setCid(21);
        customer.setUsername("username21");
        customer.setfName("William");
        customer.setlName("Gates");
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        return customers;
    }

    @Override
    public Customer getCustomerById(int cid) {
        if (customer.getCid() != cid) {
            customer.setUsername("Customer Not Found");
            customer.setfName("Customer Not Found");
            customer.setlName("Customer Not Found");
        }
        return customer;
    }

    @Override
    public Customer addCustomer(Customer customer) {
        if (customer.getUsername() == null || customer.getUsername().trim().isEmpty()) {
            customer.setUsername("Name blank, customer NOT added");
            customer.setfName("Name blank, customer NOT added");
            customer.setlName("Name blank, customer NOT added");
        }
        return customer;
    }

    @Override
    public void updateCustomer(Customer customer) {
        this.customer.setCid(customer.getCid());
        this.customer.setUsername(customer.getUsername());
        this.customer.setfName(customer.getfName());
        this.customer.setlName(customer.getlName());
    }

    @Override
    public void deleteCustomer(int cid) {
        if (customer.getCid() == cid) {
            customer.setUsername("Customer Not Found");
            customer.setfName("Customer Not Found");
            customer.setlName("Customer Not Found");
        }
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        return null;
    }
}
