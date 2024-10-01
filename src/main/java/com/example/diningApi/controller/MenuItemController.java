package com.example.diningApi.controller;

import com.example.diningApi.model.MenuItem;
import com.example.diningApi.model.Restaurant;
import com.example.diningApi.repository.MenuItemRepository;
import com.example.diningApi.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/menuitems")
public class MenuItemController {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    // Get all menu items
    @GetMapping
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    // Get a single menu item by id
    @GetMapping("/{id}")
    public Optional<MenuItem> getMenuItemById(@PathVariable Long id) {
        return menuItemRepository.findById(id);
    }

    // Create a new menu item with a restaurant association
    @PostMapping
    public MenuItem createMenuItem(@RequestBody MenuItem menuItem, @RequestParam Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .map(restaurant -> {
                    menuItem.setRestaurant(restaurant);
                    return menuItemRepository.save(menuItem);
                })
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }

    // Update an existing menu item
    @PutMapping("/{id}")
    public MenuItem updateMenuItem(@PathVariable Long id, @RequestBody MenuItem menuItemDetails, @RequestParam Long restaurantId) {
        return menuItemRepository.findById(id)
                .map(menuItem -> {
                    menuItem.setName(menuItemDetails.getName());
                    menuItem.setPrice(menuItemDetails.getPrice());

                    // Update restaurant association if needed
                    restaurantRepository.findById(restaurantId).ifPresent(menuItem::setRestaurant);

                    return menuItemRepository.save(menuItem);
                })
                .orElseThrow(() -> new RuntimeException("MenuItem not found"));
    }

    // Delete a menu item
    @DeleteMapping("/{id}")
    public void deleteMenuItem(@PathVariable Long id) {
        menuItemRepository.deleteById(id);
    }
}
