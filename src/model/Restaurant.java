package model;

import java.util.HashMap;
import java.util.Map;

public class Restaurant {

    private final String restaurantId;
    private final String name;
    private final int max_no_of_orders;
    private int currentOrders;
    private final Map<String,Integer> menu;
    private final double rating;
    public Restaurant(String restaurantId, String name, int max_no_of_orders, double rating){
        this.restaurantId = restaurantId;
        this.name = name;
        this.max_no_of_orders = max_no_of_orders;
        this.rating = rating;
        this.currentOrders = 0;
        this.menu = new HashMap<>();
    }

    public void addMenuItem(String item, int price) {
        menu.put(item, price);
    }

    public void updateMenuItem(String item, int price) {
        if (menu.containsKey(item)) {
            menu.put(item, price);
        }
    }

    public void canAcceptOrder(Map<String, Integer> items) {
        if (currentOrders >= max_no_of_orders) {
            System.out.println("Restaurant "+getName()+" can't accept the order");
            return;
        }
        System.out.println("Restaurant "+getName()+" can accept the order");
    }

    public void acceptOrder() {
        if (currentOrders < max_no_of_orders) {
            currentOrders++;
        }
    }

    public void completeOrder() {
        if (currentOrders > 0) {
            currentOrders--;
        }
    }

    public Map<String, Integer> getMenu() {
        return menu;
    }

    public double getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }

    public int getMax_no_of_orders() {
        return max_no_of_orders;
    }

    public int getCurrentOrders() {
        return currentOrders;
    }

    public String getRestaurantId(){return restaurantId;}

    public int getMenuItemPrice(String item){
        if (menu.containsKey(item)) {
            return menu.get(item);
        }
        return 0;
    }
    public boolean hasMenuItem(String item){
        return menu.containsKey(item);
    }

    @Override
    public String toString(){
        String s1="Name: "+getName()+"\n";
        s1+="Max_no_of_orders: "+getMax_no_of_orders()+"\n";
        s1+="items: "+getMenu().toString()+"\n";
        s1+="reating: "+getRating()+"\n";
        s1+="restaurant_id: "+getRestaurantId()+"\n";
        return s1;
    }
}
