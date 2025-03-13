package repository.repositoryImplementation;

import enums.OrderStatus;
import enums.SelectionStrategyType;
import model.Order;
import model.Restaurant;
import repository.OrderRepository;
import repository.RestaurantRepository;
import service.serviceImplementation.OrderServiceImplementation;
import strategies.OrderSelectionStrategy;
import strategies.strategiesImplementation.HighestRatingStrategy;
import strategies.strategiesImplementation.LowestCostStrategy;

import java.util.*;

import util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static enums.SelectionStrategyType.HIGHEST_RATING;
import static enums.SelectionStrategyType.LOWEST_COST;

public class OrderRepositoryImplementation implements OrderRepository {
    private final List<Order> orders = new ArrayList<>();
    private final RestaurantRepository restaurantRepository;
    private final Map<String, Order> activeOrders;
    private final ScheduledExecutorService scheduler;
    private static OrderRepository instance;
    private OrderRepositoryImplementation() {
        this.restaurantRepository=RestaurantRepositoryImplementation.getInstance();
        this.activeOrders=new HashMap<>();
        this.scheduler = Executors.newScheduledThreadPool(5);
    }
    public static synchronized OrderRepositoryImplementation getInstance() {
        if (instance == null) {
            instance = new OrderRepositoryImplementation();
        }
        return (OrderRepositoryImplementation) instance;
    }
    @Override
    public void placeOrder(String user, Map<String, Integer> items, SelectionStrategyType strategyType) {

        Restaurant bestRestaurant = findBestRestaurant(items, strategyType);
        try {
            if (bestRestaurant == null) {
                System.out.println("Cannot assign the order (No restaurant can fulfill it)");
                return;
            }

            if (bestRestaurant.getCurrentOrders() >= bestRestaurant.getMax_no_of_orders()) {
                System.out.println("Restaurant " + bestRestaurant.getName() + " has reached max orders.");
                return;
            }

            // Assign order
            bestRestaurant.acceptOrder();
            String placedOrderId = Utils.getUUid();
            Order order = new Order(placedOrderId, user, items, strategyType);
            order.assignRestaurant(bestRestaurant);
            orders.add(order);
            System.out.println("Order assigned to " + bestRestaurant.getName() + " (User: " + user + ")");
            order.markAsCompleted();
            completeOrder(order.getOrderId());
            bestRestaurant.completeOrder();

        } catch (Exception e){
            System.out.println(e.toString());
        }
    }

    // Find the best restaurant based on strategy
    private Restaurant findBestRestaurant(Map<String, Integer> items, SelectionStrategyType strategy) {
        List<Restaurant> validRestaurants = new ArrayList<>();

        for (Restaurant restaurant : restaurantRepository.getAllRestaurants()) {
            if (restaurantRepository.canFulfillOrder(restaurant, items) && restaurant.getCurrentOrders() < restaurant.getMax_no_of_orders()) {
                validRestaurants.add(restaurant);
            }
        }
        try {
            if (validRestaurants.isEmpty()) return null;
            if (strategy.name().equals("LOWEST_COST")) {
                return validRestaurants.stream().min(Comparator.comparingInt(r -> calculateOrderCost(r, items))).orElse(null);
            } else if (strategy.name().equals("HIGHEST_RATING")) {
                return validRestaurants.stream().max(Comparator.comparingDouble(Restaurant::getRating)).orElse(null);
            }
        } catch (Exception e){
            System.out.println(e.toString());

        }
        return null;
    }

    // Calculate total order cost for a restaurant
    private int calculateOrderCost(Restaurant restaurant, Map<String, Integer> items) {
        int totalCost = 0;
        try {
            for (Map.Entry<String, Integer> entry : items.entrySet()) {
                totalCost += restaurant.getMenu().get(entry.getKey()) * entry.getValue();
            }
        } catch (Exception e){
            System.out.println(e.toString());
        }
        return totalCost;
    }

    @Override
    public void completeOrder(String orderId) {
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId) && order.getStatus() == OrderStatus.ACCEPTED) {
                order.markAsCompleted();
                System.out.println("Order " + orderId + " marked as COMPLETED.");
                return;
            }
        }
        System.out.println("Order not found or already completed.");
    }
}

