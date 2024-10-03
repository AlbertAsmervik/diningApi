package com.example.diningApi.controller;

import com.example.diningApi.model.Restaurant;
import com.example.diningApi.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    // Get all restaurants
    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    // Get a restaurant by ID
    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id) {
        // Validation: Check if the restaurant exists
        return restaurantService.getRestaurantById(id);
    }

    // Create a new restaurant
    @PostMapping
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.createRestaurant(restaurant);
    }

    // Update an existing restaurant
    @PutMapping("/{id}")
    public Restaurant updateRestaurant(@PathVariable Long id, @RequestBody Restaurant updatedRestaurant) {
        // Validation: Check if the restaurant exists
        if (!restaurantService.existsById(id)) {
            throw new RuntimeException("Restaurant not found.");
        }

        return restaurantService.updateRestaurant(id, updatedRestaurant);
    }

    // Delete a restaurant by ID
    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable Long id) {
        // Validation: Check if the restaurant exists before deletion
        if (!restaurantService.existsById(id)) {
            throw new RuntimeException("Restaurant not found.");
        }

        restaurantService.deleteRestaurant(id);
    }

    // Search restaurants by zip code and allergy type (e.g., peanut, egg, dairy)
    @GetMapping("/search")
    public List<Restaurant> searchRestaurantsByZipAndAllergy(
            @RequestParam String zipCode,
            @RequestParam String allergyType) {
        return restaurantService.getRestaurantsByZipCodeAndAllergyScore(zipCode, allergyType);
    }

    // Admin-specific: Get all restaurants (with possible admin privileges)
    @GetMapping("/admin/all")
    public List<Restaurant> getAllRestaurantsAdmin() {
        return restaurantService.getAllRestaurants();
    }
}
