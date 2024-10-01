package com.example.diningApi.controller;

import com.example.diningApi.model.User;
import com.example.diningApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users") // Update the request mapping to match "User"
public class UserController { // Update class name to "UserController"

    @Autowired
    private UserRepository userRepository; // Update the repository type

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get a single user by id
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    // Create a new user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // Update an existing user
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setDisplayName(userDetails.getDisplayName());
                    user.setCity(userDetails.getCity());
                    user.setState(userDetails.getState());
                    user.setZipcode(userDetails.getZipcode());
                    user.setInterestedInPeanutAllergies(userDetails.getInterestedInPeanutAllergies());
                    user.setInterestedInEggAllergies(userDetails.getInterestedInEggAllergies());
                    user.setInterestedInDairyAllergies(userDetails.getInterestedInDairyAllergies());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    userDetails.setId(id);
                    return userRepository.save(userDetails);
                });
    }

    // Delete a user
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}

