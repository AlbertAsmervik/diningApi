package com.example.diningApi.controller;

import com.example.diningApi.model.Customer;
import com.example.diningApi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers") // Update the request mapping to match "Customer"
public class CustomerController { // Update class name to "CustomerController"

    @Autowired
    private CustomerRepository CustomerRepository; // Update the repository type

    // Get all customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return CustomerRepository.findAll();
    }

    // Get a single customer by id
    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable Long id) {
        return CustomerRepository.findById(id);
    }

    // Create a new customer
    @PostMapping
    public Customer createCustomer(@RequestBody Customer Customer) {
        return CustomerRepository.save(Customer);
    }

    // Update an existing customer
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer CustomerDetails) {
        return CustomerRepository.findById(id)
                .map(Customer -> {
                    Customer.setDisplayName(CustomerDetails.getDisplayName());
                    Customer.setCity(CustomerDetails.getCity());
                    Customer.setState(CustomerDetails.getState());
                    Customer.setZipcode(CustomerDetails.getZipcode());
                    Customer.setInterestedInPeanutAllergies(CustomerDetails.getInterestedInPeanutAllergies());
                    Customer.setInterestedInEggAllergies(CustomerDetails.getInterestedInEggAllergies());
                    Customer.setInterestedInDairyAllergies(CustomerDetails.getInterestedInDairyAllergies());
                    return CustomerRepository.save(Customer);
                })
                .orElseGet(() -> {
                    CustomerDetails.setId(id);
                    return CustomerRepository.save(CustomerDetails);
                });
    }

    // Delete a customer
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        CustomerRepository.deleteById(id);
    }
}

