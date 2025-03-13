package strategies;

import model.Restaurant;
import java.util.List;
import java.util.Map;

public interface OrderSelectionStrategy {
    Restaurant selectRestaurant(List<Restaurant> restaurants, Map<String, Integer> items);
}


