package strategies.strategiesImplementation;

import model.Restaurant;
import strategies.OrderSelectionStrategy;
import java.util.List;
import java.util.Map;
import java.util.Comparator;

public class HighestRatingStrategy implements OrderSelectionStrategy {
    @Override
    public Restaurant selectRestaurant(List<Restaurant> restaurants, Map<String, Integer> items) {
        return restaurants.stream()
                .filter(r -> canFulfillOrder(r, items))
                .max(Comparator.comparingDouble(Restaurant::getRating))
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
}
