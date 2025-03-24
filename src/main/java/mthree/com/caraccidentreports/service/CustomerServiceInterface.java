package mthree.com.caraccidentreports.service;

import mthree.com.caraccidentreports.model.Customer;

import java.util.List;

public interface CustomerServiceInterface {
    Customer addNewCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(int cid);
    Customer updateCustomer(int cid, Customer customer);
    void deleteCustomer(int cid);
}
