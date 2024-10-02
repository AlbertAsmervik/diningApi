package com.example.diningApi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiningReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Who submitted the review (User's display name)
    private String displayName;

    // Reference to the restaurant being reviewed
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    // Optional scores (scale 1-5)
    @Column(nullable = true)
    private Integer peanutScore;

    @Column(nullable = true)
    private Integer eggScore;

    @Column(nullable = true)
    private Integer dairyScore;

    // Optional commentary
    private String commentary;

    @Enumerated(EnumType.STRING) // Store enum as a String in the DB
    private ReviewStatus status = ReviewStatus.PENDING; // Default status is PENDING
}
