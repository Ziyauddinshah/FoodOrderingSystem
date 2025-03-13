package service;

import model.Restaurant;
import repository.RestaurantRepository;
import service.serviceImplementation.RestaurantServiceImplementation;

import java.util.*;

public interface RestaurantService {

    // Onboard a new restaurant
    void onboardRestaurant(Restaurant restaurant);
    // Add or Update menu Item
    void addOrUpdateMenuItem(String restaurantName, String item, int price);
    // Get Restaurant by name
    Restaurant getRestaurantByName(String name);
    // Get list of restaurant data
    List<Restaurant> getAllRestaurants();
    // Show all restaurant
    void listRestaurants();
    boolean canFulfillOrder(Restaurant restaurant, Map<String, Integer> orderItems);
}
