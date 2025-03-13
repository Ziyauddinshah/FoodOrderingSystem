package strategies.strategiesImplementation;

import model.Restaurant;
import strategies.OrderSelectionStrategy;
import java.util.List;
import java.util.Map;
import java.util.Comparator;

public class LowestCostStrategy implements OrderSelectionStrategy {
    @Override
    public Restaurant selectRestaurant(List<Restaurant> restaurants, Map<String, Integer> items) {
        return restaurants.stream()
                .filter(r -> canFulfillOrder(r, items))
                .min(Comparator.comparingInt(r -> calculateOrderCost(r, items)))
                .orElse(null);
    }
    private boolean canFulfillOrder(Restaurant restaurant, Map<String, Integer> orderItems) {
        for (String item : orderItems.keySet()) {
            if (!restaurant.getMenu().containsKey(item)) {
                return false;
            }
        }
        return true;
    }
    private int calculateOrderCost(Restaurant restaurant, Map<String, Integer> items) {
        int totalCost = 0;
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            totalCost += restaurant.getMenu().get(entry.getKey()) * entry.getValue();
        }
        return totalCost;
    }
}
