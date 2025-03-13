package repository;

import enums.OrderStatus;
import enums.SelectionStrategyType;
import strategies.OrderSelectionStrategy;
import model.Order;
import model.Restaurant;
import strategies.strategiesImplementation.HighestRatingStrategy;
import strategies.strategiesImplementation.LowestCostStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface OrderRepository {
    void placeOrder(String user, Map<String, Integer> items, SelectionStrategyType strategyType);
    void completeOrder(String orderId);
}
