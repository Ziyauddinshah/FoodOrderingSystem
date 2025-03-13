package repository.repositoryImplementation;

import model.Restaurant;

import java.util.*;


import model.Restaurant;
import repository.RestaurantRepository;
import service.serviceImplementation.RestaurantServiceImplementation;

import java.util.ArrayList;
import java.util.List;

public class RestaurantRepositoryImplementation implements RestaurantRepository {
    private final List<Restaurant> restaurants;
    private static RestaurantRepository instance;
    private RestaurantRepositoryImplementation(){
        this.restaurants=new ArrayList<>();
    }
    public static synchronized RestaurantRepositoryImplementation getInstance() {
        if (instance == null) {
            instance = new RestaurantRepositoryImplementation();
        }
        return (RestaurantRepositoryImplementation) instance;
    }
    // Onboard a new restaurant
    @Override
    public void onboardRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
        System.out.println("Restaurant " + restaurant.getName() + " onboarded successfully.");
    }

    // Add or update a menu item
    @Override
    public void addOrUpdateMenuItem(String restaurantName, String item, int price) {
        for (Restaurant r : restaurants) {
            if (r.getName().equals(restaurantName)) {
                if (r.getMenu().containsKey(item)) {
                    r.updateMenuItem(item, price); // Update existing item price
                    System.out.println("Updated price of " + item + " in " + restaurantName);
                } else {
                    r.addMenuItem(item, price); // Add new item
                    System.out.println("Added new item " + item + " in " + restaurantName);
                }
                return;
            }
        }
        System.out.println("Restaurant " + restaurantName + " not found.");
    }

    // Get a restaurant by name
    @Override
    public Restaurant getRestaurantByName(String name) {
        return restaurants.stream()
                .filter(r -> r.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    //Get all restaurants
    @Override
    public List<Restaurant> getAllRestaurants(){
        return restaurants;
    }

    // List all onboarded restaurants
    @Override
    public void listRestaurants() {
        if (restaurants.isEmpty()) {
            System.out.println("No restaurants available.");
            return;
        }

        System.out.println("Available Restaurants:");
        for (Restaurant r : restaurants) {
            System.out.println("- " + r.getName() + " (Rating: " + r.getRating() + ")");
        }
    }

    @Override
    public boolean canFulfillOrder(Restaurant restaurant, Map<String, Integer> orderItems) {
        for (String item : orderItems.keySet()) {
            if (!restaurant.getMenu().containsKey(item)) {
                System.out.println("Restaurant "+restaurant.getName()+" can not fulfill the order");
                return false;
            }
        }
        System.out.println("Restaurant "+restaurant.getName()+" can fulfill the order");
        return true;
    }
}

