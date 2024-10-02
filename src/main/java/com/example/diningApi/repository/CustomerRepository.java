package com.example.diningApi.repository;

import com.example.diningApi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByDisplayName(String displayName);

    Boolean existsByDisplayName(String displayName);
}

