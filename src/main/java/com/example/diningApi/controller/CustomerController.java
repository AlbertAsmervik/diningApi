package com.example.diningApi.controller;

import com.example.diningApi.model.Customer;
import com.example.diningApi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    // Get all customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get a single customer by displayName
    @GetMapping("/{displayName}")
    public Optional<Customer> getCustomerByDisplayName(@PathVariable String displayName) {
        return customerRepository.findByDisplayName(displayName);
    }

    // Create a new customer
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    // Update an existing customer by displayName
    @PutMapping("/{displayName}")
    public Customer updateCustomer(@PathVariable String displayName, @RequestBody Customer customerDetails) {
        return customerRepository.findByDisplayName(displayName)
                .map(customer -> {
                    customer.setCity(customerDetails.getCity());
                    customer.setState(customerDetails.getState());
                    customer.setZipcode(customerDetails.getZipcode());
                    customer.setInterestedInPeanutAllergies(customerDetails.getInterestedInPeanutAllergies());
                    customer.setInterestedInEggAllergies(customerDetails.getInterestedInEggAllergies());
                    customer.setInterestedInDairyAllergies(customerDetails.getInterestedInDairyAllergies());
                    return customerRepository.save(customer);
                })
                .orElseThrow(() -> new RuntimeException("Customer not found with displayName: " + displayName));
    }

    // Delete a customer by displayName
    @DeleteMapping("/{displayName}")
    public void deleteCustomer(@PathVariable String displayName) {
        customerRepository.findByDisplayName(displayName)
                .ifPresent(customer -> customerRepository.delete(customer));
    }
}



