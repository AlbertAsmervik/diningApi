package com.example.diningApi.repository;

import com.example.diningApi.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    // Check for duplicate restaurants by name and zip code
    boolean existsByNameAndZipCode(String name, String zipCode);

    // Custom query to fetch restaurants by zip code with at least one allergy score, sorted by overall score descending
    @Query("SELECT r FROM Restaurant r WHERE r.zipCode = :zipCode AND (r.peanutScore IS NOT NULL OR r.eggScore IS NOT NULL OR r.dairyScore IS NOT NULL) ORDER BY r.overallScore DESC")
    List<Restaurant> findByZipCodeAndAllergyScoresPresent(String zipCode);

    // Check if a restaurant with the same name and zip code exists
    Optional<Restaurant> findByNameAndZipCode(String name, String zipCode);

    List<Restaurant> findByZipCodeAndPeanutScoreIsNotNullOrderByPeanutScoreDesc(String zipCode);

    List<Restaurant> findByZipCodeAndEggScoreIsNotNullOrderByEggScoreDesc(String zipCode);

    List<Restaurant> findByZipCodeAndDairyScoreIsNotNullOrderByDairyScoreDesc(String zipCode);





}

