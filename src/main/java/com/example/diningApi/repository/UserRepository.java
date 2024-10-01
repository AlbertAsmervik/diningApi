package com.example.diningApi.repository;

import com.example.diningApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByDisplayName(String displayName);

    Boolean existsByDisplayName(String displayName);
}

