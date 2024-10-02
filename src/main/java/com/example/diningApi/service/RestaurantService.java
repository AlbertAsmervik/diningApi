package com.example.diningApi.service;

import com.example.diningApi.model.Restaurant;
import com.example.diningApi.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    private final DecimalFormat decimalFormat = new DecimalFormat("#.00");

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    // Get all restaurants
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    // Fetch a restaurant by its unique ID
    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }

    // Create a new restaurant (with duplicate check)
    public Restaurant createRestaurant(Restaurant restaurant) {
        Optional<Restaurant> existingRestaurant = restaurantRepository.findByNameAndZipCode(
                restaurant.getName(), restaurant.getZipCode());
        if (existingRestaurant.isPresent()) {
            throw new RuntimeException("A restaurant with this name and zip code already exists.");
        }
        return formatRestaurantScores(restaurantRepository.save(restaurant));
    }

    // Update a restaurant
    public Restaurant updateRestaurant(Long id, Restaurant updatedRestaurant) {
        return restaurantRepository.findById(id)
                .map(restaurant -> {
                    restaurant.setName(updatedRestaurant.getName());
                    restaurant.setPeanutScore(updatedRestaurant.getPeanutScore());
                    restaurant.setEggScore(updatedRestaurant.getEggScore());
                    restaurant.setDairyScore(updatedRestaurant.getDairyScore());
                    restaurant.setOverallScore(updatedRestaurant.getOverallScore());
                    return formatRestaurantScores(restaurantRepository.save(restaurant));
                })
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }

    // Delete a restaurant
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }

    // Search restaurants by zip code and allergy type
    public List<Restaurant> getRestaurantsByZipCodeAndAllergyScore(String zipCode, String allergyType) {
        switch (allergyType.toLowerCase()) {
            case "peanut":
                return formatRestaurantScores(restaurantRepository.findByZipCodeAndPeanutScoreIsNotNullOrderByPeanutScoreDesc(zipCode));
            case "egg":
                return formatRestaurantScores(restaurantRepository.findByZipCodeAndEggScoreIsNotNullOrderByEggScoreDesc(zipCode));
            case "dairy":
                return formatRestaurantScores(restaurantRepository.findByZipCodeAndDairyScoreIsNotNullOrderByDairyScoreDesc(zipCode));
            default:
                throw new IllegalArgumentException("Invalid allergy type: " + allergyType);
        }
    }

    // Format the scores to two decimal places
    private Restaurant formatRestaurantScores(Restaurant restaurant) {
        restaurant.setPeanutScore(formatScore(restaurant.getPeanutScore()));
        restaurant.setEggScore(formatScore(restaurant.getEggScore()));
        restaurant.setDairyScore(formatScore(restaurant.getDairyScore()));
        restaurant.setOverallScore(formatScore(restaurant.getOverallScore()));
        return restaurant;
    }

    // Format the scores in a list of restaurants
    private List<Restaurant> formatRestaurantScores(List<Restaurant> restaurants) {
        for (Restaurant restaurant : restaurants) {
            formatRestaurantScores(restaurant);
        }
        return restaurants;
    }

    // Helper method to format score
    private Double formatScore(Double score) {
        return score == null ? null : Double.valueOf(decimalFormat.format(score));
    }
}

