package service;

import enums.SelectionStrategyType;
import repository.OrderRepository;

import java.util.*;

public interface OrderService {
    void placeOrder(String user, Map<String, Integer> items, SelectionStrategyType strategyType);

    void completeOrder(String orderId);
}
