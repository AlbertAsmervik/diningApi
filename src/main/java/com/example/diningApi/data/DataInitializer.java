package com.example.diningApi.data;
// this whole initilaizer is kinda reluctant, in the way that it sets all the values so the other
// components are useless
import com.example.diningApi.model.*;
import com.example.diningApi.repository.RestaurantRepository;
import com.example.diningApi.repository.DiningReviewRepository;
import com.example.diningApi.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RestaurantRepository restaurantRepository;
    private final DiningReviewRepository diningReviewRepository;
    private final CustomerRepository customerRepository;

    public DataInitializer(RestaurantRepository restaurantRepository, DiningReviewRepository diningReviewRepository, CustomerRepository customerRepository) {
        this.restaurantRepository = restaurantRepository;
        this.diningReviewRepository = diningReviewRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Restaurant restaurant1 = new Restaurant();
        restaurant1.setName("The Veggie Spot");
        restaurant1.setZipCode("12345");
        restaurant1.setPeanutScore(4.5);
        restaurant1.setEggScore(4.0);
        restaurant1.setDairyScore(5.0);
        restaurant1.setOverallScore(null);

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setName("Burger Haven");
        restaurant2.setZipCode("54321");
        restaurant2.setPeanutScore(3.0);
        restaurant2.setEggScore(3.5);
        restaurant2.setDairyScore(4.0);
        restaurant2.setOverallScore(null);

        Restaurant restaurant3 = new Restaurant();
        restaurant2.setName("Burger Maven");
        restaurant2.setZipCode("32323");
        restaurant2.setPeanutScore(3.0);
        restaurant2.setEggScore(3.5);
        restaurant2.setDairyScore(4.0);
        restaurant2.setOverallScore(null);

        restaurantRepository.save(restaurant1);
        restaurantRepository.save(restaurant2);
        restaurantRepository.save(restaurant3);

        // Adding Dummy Customers
        Customer customer1 = new Customer();
        customer1.setDisplayName("JohnDoe");
        customer1.setCity("Recife");
        customer1.setState("PE");
        customer1.setZipcode("52000");
        customer1.setInterestedInPeanutAllergies(true);
        customer1.setInterestedInEggAllergies(false);
        customer1.setInterestedInDairyAllergies(true);

        Customer customer2 = new Customer();
        customer2.setDisplayName("JaneDoe");
        customer2.setCity("São Paulo");
        customer2.setState("SP");
        customer2.setZipcode("01000");
        customer2.setInterestedInPeanutAllergies(false);
        customer2.setInterestedInEggAllergies(true);
        customer2.setInterestedInDairyAllergies(false);

        customerRepository.save(customer1);
        customerRepository.save(customer2);


        DiningReview review1 = new DiningReview();
        review1.setDisplayName("JohnDoe");
        review1.setRestaurant(restaurant1);
        review1.setPeanutScore(4);
        review1.setEggScore(4);
        review1.setDairyScore(5);
        review1.setCommentary("Great experience with allergies in mind!");
        review1.setStatus(ReviewStatus.APPROVED);

        DiningReview review2 = new DiningReview();
        review2.setDisplayName("JaneDoe");
        review2.setRestaurant(restaurant2);
        review2.setPeanutScore(3);
        review2.setEggScore(4);
        review2.setDairyScore(3);
        review2.setCommentary("Food was decent, but could improve allergy options.");
        review2.setStatus(ReviewStatus.PENDING);

        DiningReview review3 = new DiningReview();
        review2.setDisplayName("Markus");
        review2.setRestaurant(restaurant3);
        review2.setPeanutScore(2);
        review2.setEggScore(1);
        review2.setDairyScore(2);
        review2.setCommentary("Food was bland.");
        review2.setStatus(ReviewStatus.PENDING);

        diningReviewRepository.save(review1);
        diningReviewRepository.save(review2);
        diningReviewRepository.save(review3);
    }
}
