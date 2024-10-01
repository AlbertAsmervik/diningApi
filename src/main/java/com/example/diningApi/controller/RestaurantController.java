package com.example.diningApi.controller;

import com.example.diningApi.model.Restaurant;
import com.example.diningApi.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    // Get all restaurants
    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    // Get a restaurant by ID
    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }

    // Create a new restaurant
    @PostMapping
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        // Check for duplicate restaurant based on name and zip code
        boolean exists = restaurantRepository.existsByNameAndZipCode(restaurant.getName(), restaurant.getZipCode());
        if (exists) {
            throw new RuntimeException("Restaurant with the same name and zip code already exists");
        }

        return restaurantRepository.save(restaurant);
    }

    // Update an existing restaurant
    @PutMapping("/{id}")
    public Restaurant updateRestaurant(@PathVariable Long id, @RequestBody Restaurant updatedRestaurant) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        // Update restaurant details
        restaurant.setName(updatedRestaurant.getName());
        restaurant.setPeanutScore(updatedRestaurant.getPeanutScore());
        restaurant.setEggScore(updatedRestaurant.getEggScore());
        restaurant.setDairyScore(updatedRestaurant.getDairyScore());
        restaurant.setOverallScore(updatedRestaurant.getOverallScore()); // Ensure this field is updated

        return restaurantRepository.save(restaurant);
    }

    // Delete a restaurant by ID
    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable Long id) {
        restaurantRepository.deleteById(id);
    }

    // Fetch restaurants by zip code with allergy scores in descending order
    @GetMapping("/zip/{zipCode}")
    public List<Restaurant> getRestaurantsByZipCodeWithScores(@PathVariable String zipCode) {
        return restaurantRepository.findByZipCodeAndAllergyScoresPresent(zipCode);
    }

    // Optional: Helper method to calculate the overall score
    private Double calculateOverallScore(Restaurant restaurant) {
        Double peanutScore = restaurant.getPeanutScore();
        Double eggScore = restaurant.getEggScore();
        Double dairyScore = restaurant.getDairyScore();

        return (peanutScore + eggScore + dairyScore) / 3;
    }
}
