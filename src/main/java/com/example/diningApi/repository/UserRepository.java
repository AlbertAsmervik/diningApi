package com.example.diningApi.repository;

import com.example.diningApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Custom query method to find a user by their unique display name.
     * This is useful for fetching a user profile based on the display name,
     * especially when handling user-submitted dining reviews.
     *
     * @param displayName the unique display name of the user
     * @return an Optional containing the user if found, otherwise empty
     */
    Optional<User> findByDisplayName(String displayName);

    /**
     * Custom query method to check if a user exists by their display name.
     * This is useful for validating that the display name is unique
     * when creating or updating a user profile.
     *
     * @param displayName the unique display name of the user
     * @return true if a user with the display name exists, false otherwise
     */
    Boolean existsByDisplayName(String displayName);
}

