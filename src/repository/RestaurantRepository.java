package repository;

import model.Restaurant;
import java.util.List;
import java.util.Map;

public interface RestaurantRepository {

    // Onboard a new restaurant
    void onboardRestaurant(Restaurant restaurant);
    // Add or update a menu item
    void addOrUpdateMenuItem(String restaurantName, String item, int price);
    // Get a restaurant by name
    Restaurant getRestaurantByName(String name);
    //Get all restaurants
    List<Restaurant> getAllRestaurants();
    // List all onboarded restaurants
    void listRestaurants();
    boolean canFulfillOrder(Restaurant restaurant, Map<String, Integer> orderItems);
}
