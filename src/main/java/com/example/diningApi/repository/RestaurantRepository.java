// File: repository/RestaurantRepository.java

package com.example.diningApi.repository;

import com.example.diningApi.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    // JpaRepository provides basic CRUD operations automatically.

    // Additional custom queries can be defined here as needed.
}

