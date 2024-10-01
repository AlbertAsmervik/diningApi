package com.example.diningApi.service;

import com.example.diningApi.model.Restaurant;
import com.example.diningApi.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    // Check if a restaurant with the same name and zip code exists
    public Restaurant createRestaurant(Restaurant restaurant) {
        Optional<Restaurant> existingRestaurant = restaurantRepository.findByNameAndZipCode(
                restaurant.getName(), restaurant.getZipCode());
        if (existingRestaurant.isPresent()) {
            throw new RuntimeException("A restaurant with this name and zip code already exists.");
        }
        return restaurantRepository.save(restaurant);
    }

    // Fetch a restaurant by its unique ID
    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }

    // Fetch restaurants by zip code and allergy score
    public List<Restaurant> getRestaurantsByZipCodeAndAllergyScore(String zipCode, String allergyType) {
        switch (allergyType.toLowerCase()) {
            case "peanut":
                return restaurantRepository.findByZipCodeAndPeanutScoreIsNotNullOrderByPeanutScoreDesc(zipCode);
            case "egg":
                return restaurantRepository.findByZipCodeAndEggScoreIsNotNullOrderByEggScoreDesc(zipCode);
            case "dairy":
                return restaurantRepository.findByZipCodeAndDairyScoreIsNotNullOrderByDairyScoreDesc(zipCode);
            default:
                throw new IllegalArgumentException("Invalid allergy type: " + allergyType);
        }
    }
}
