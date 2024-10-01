package com.example.diningApi.repository;

import com.example.diningApi.model.DiningReview;
import com.example.diningApi.model.Restaurant;
import com.example.diningApi.model.ReviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiningReviewRepository extends JpaRepository<DiningReview, Long> {

    // Fetch all dining reviews that are pending approval
    List<DiningReview> findByStatus(ReviewStatus status);

    // Fetch all dining reviews for a specific restaurant that are approved
    List<DiningReview> findByRestaurantAndStatus(Restaurant restaurant, ReviewStatus status);

    // Custom query to fetch approved dining reviews for a restaurant by restaurant ID
    @Query("SELECT r FROM DiningReview r WHERE r.restaurant.id = :restaurantId AND r.status = 'APPROVED'")
    List<DiningReview> findApprovedReviewsByRestaurantId(Long restaurantId);
}
