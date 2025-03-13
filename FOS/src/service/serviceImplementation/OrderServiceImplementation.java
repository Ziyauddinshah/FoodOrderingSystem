package service.serviceImplementation;

import enums.SelectionStrategyType;
import model.Restaurant;
import repository.OrderRepository;

import java.util.Map;

import enums.SelectionStrategyType;
import repository.OrderRepository;
import repository.RestaurantRepository;
import repository.repositoryImplementation.OrderRepositoryImplementation;
import repository.repositoryImplementation.RestaurantRepositoryImplementation;
import service.OrderService;
import service.RestaurantService;

import java.util.*;

public class OrderServiceImplementation implements OrderService {
    private static OrderServiceImplementation instance;
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;

    private OrderServiceImplementation() {
        this.orderRepository=OrderRepositoryImplementation.getInstance();
        this.restaurantRepository=RestaurantRepositoryImplementation.getInstance();
    }
    public static synchronized OrderServiceImplementation getInstance() {
        if (instance == null) {
            instance = new OrderServiceImplementation();
        }
        return instance;
    }
    @Override
    public void placeOrder(String user, Map<String, Integer> items, SelectionStrategyType strategyType) {
        orderRepository.placeOrder(user,items,strategyType);
    }
    @Override
    public void completeOrder(String orderId) {
        orderRepository.completeOrder(orderId);
    }
}

