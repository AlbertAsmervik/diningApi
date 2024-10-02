package com.example.diningApi.controller;

import com.example.diningApi.model.DiningReview;
import com.example.diningApi.model.ReviewStatus;
import com.example.diningApi.repository.DiningReviewRepository;
import com.example.diningApi.repository.RestaurantRepository;
import com.example.diningApi.model.Restaurant;
import com.example.diningApi.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/dining-reviews")
public class DiningReviewController {

    @Autowired
    private DiningReviewRepository diningReviewRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantService restaurantService;

    // Submit a dining review (registered user)
    @PostMapping
    public DiningReview submitReview(@RequestBody DiningReview diningReview) {
        return diningReviewRepository.save(diningReview);
    }

    // Get list of all dining reviews pending approval (admin)
    @GetMapping("/pending")
    public List<DiningReview> getPendingReviews() {
        return diningReviewRepository.findByStatus(ReviewStatus.PENDING);
    }

    // Approve or reject a dining review (admin)
    @PutMapping("/{id}/approve")
    public DiningReview approveReview(@PathVariable Long id, @RequestParam boolean approve) {
        Optional<DiningReview> reviewOpt = diningReviewRepository.findById(id);
        if (reviewOpt.isPresent()) {
            DiningReview review = reviewOpt.get();
            review.setStatus(approve ? ReviewStatus.APPROVED : ReviewStatus.REJECTED);
            diningReviewRepository.save(review);

            // Recompute the restaurant score when a review is approved
            if (approve) {
                restaurantService.recalculateRestaurantScore(review.getRestaurant().getId());
            }
            return review;
        } else {
            throw new RuntimeException("Review not found");
        }
    }

    // Fetch all approved dining reviews for a specific restaurant
    @GetMapping("/restaurant/{restaurantId}")
    public List<DiningReview> getApprovedReviewsForRestaurant(@PathVariable Long restaurantId) {
        return diningReviewRepository.findApprovedReviewsByRestaurantId(restaurantId);
    }
}
