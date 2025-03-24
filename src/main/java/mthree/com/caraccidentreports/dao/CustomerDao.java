package mthree.com.caraccidentreports.dao.mappers;

import mthree.com.caraccidentreports.model.Customer;

import java.util.List;

public interface CustomerDao {
    List<Customer> getAllCustomers();
    Customer getCustomerById(int cid);
    void addCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(int cid);
}
