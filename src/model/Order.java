package model;
import enums.OrderStatus;
import enums.SelectionStrategyType;
import util.Utils;

import java.util.Map;

public class Order {
    private final String orderId;
    private String user;
    private Map<String, Integer> items;
    private SelectionStrategyType strategy;
    private OrderStatus status;
    private Restaurant assignedRestaurant;

    public Order(String orderId, String user, Map<String, Integer> items, SelectionStrategyType strategy) {
        this.orderId = orderId;
        this.user = user;
        this.items = items;
        this.strategy = strategy;
        this.status = OrderStatus.PENDING;
    }

    public void assignRestaurant(Restaurant restaurant) {
        this.assignedRestaurant = restaurant;
        this.status = OrderStatus.ACCEPTED;
    }

    public void markAsCompleted() {
        if (this.status == OrderStatus.ACCEPTED) {
            this.status = OrderStatus.COMPLETED;
        }
    }

    public OrderStatus getStatus() { return status; }
    public String getOrderId(){return orderId;}
}
