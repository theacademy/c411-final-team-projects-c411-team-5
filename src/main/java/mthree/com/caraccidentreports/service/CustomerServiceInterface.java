package mthree.com.caraccidentreports.service;

import mthree.com.caraccidentreports.model.Customer;

import java.util.List;

public interface CustomerServiceInterface {
    List<Customer> getAllCustomers();
    Customer getCustomerById(int cid);
    Customer addNewCustomer(Customer customer);
    Customer updateCustomer(int cid, Customer customer);
    void deleteCustomer(int cid);
}
