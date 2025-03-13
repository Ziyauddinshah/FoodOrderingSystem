package service.serviceImplementation;

import model.Restaurant;
import repository.RestaurantRepository;

import java.util.List;
import java.util.Map;

import repository.repositoryImplementation.RestaurantRepositoryImplementation;
import service.RestaurantService;


public class RestaurantServiceImplementation implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private static RestaurantService instance;
    private RestaurantServiceImplementation(){
        this.restaurantRepository=RestaurantRepositoryImplementation.getInstance();
    }
    public static synchronized RestaurantServiceImplementation getInstance() {
        if (instance == null) {
            instance = new RestaurantServiceImplementation();
        }
        return (RestaurantServiceImplementation) instance;
    }
    // Onboard a new restaurant
    @Override
    public void onboardRestaurant(Restaurant restaurant) {
        restaurantRepository.onboardRestaurant(restaurant);
    }

    // Add or update a menu item
    @Override
    public void addOrUpdateMenuItem(String restaurantName, String item, int price) {
        restaurantRepository.addOrUpdateMenuItem(restaurantName,item,price);
    }

    // Get a restaurant by name
    @Override
    public Restaurant getRestaurantByName(String name) {
        return restaurantRepository.getRestaurantByName(name);
    }

    //Get all restaurants
    @Override
    public List<Restaurant> getAllRestaurants(){
        return restaurantRepository.getAllRestaurants();
    }

    // List all onboarded restaurants
    @Override
    public void listRestaurants() {
        restaurantRepository.listRestaurants();
    }

    @Override
    public boolean canFulfillOrder(Restaurant restaurant, Map<String, Integer> orderItems){
        return restaurantRepository.canFulfillOrder(restaurant,orderItems);
    }
}

