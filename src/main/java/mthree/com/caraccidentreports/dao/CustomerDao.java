package mthree.com.caraccidentreports.dao.mappers;

import mthree.com.caraccidentreports.model.Customer;

import java.util.List;

public interface CustomerDao {
    Customer addCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(int cid);
    void updateCustomer(Customer customer);
    void deleteCustomer(int cid);
}
