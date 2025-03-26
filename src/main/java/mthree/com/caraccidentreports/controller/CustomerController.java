package mthree.com.caraccidentreports.controller;

import mthree.com.caraccidentreports.model.Customer;
import mthree.com.caraccidentreports.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    CustomerServiceImpl customerService;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{cid}")
    public Customer getCustomerById(@PathVariable int cid) {
        return customerService.getCustomerById(cid);
    }

    @GetMapping("/find/{username}")
    public Customer findCustomerByUsername(@PathVariable String username){
        return customerService.getCustomerByUsername(username);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
        try {
            customerService.addNewCustomer(customer);
            return ResponseEntity.ok("Customer added successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred while creating the customer.");
        }
    }
    @PutMapping("/{cid}")
    public Customer updateCustomer(@PathVariable int cid, @RequestBody Customer customer) {
        return customerService.updateCustomer(cid, customer);
    }

    @DeleteMapping("/{cid}")
    public void deleteCustomer(@PathVariable int cid) {
        customerService.deleteCustomer(cid);
    }
}
