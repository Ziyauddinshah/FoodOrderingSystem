import enums.SelectionStrategyType;
import model.Restaurant;
import service.OrderService;
import service.RestaurantService;
import service.serviceImplementation.OrderServiceImplementation;
import service.serviceImplementation.RestaurantServiceImplementation;
import util.Utils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Driver {
    static RestaurantService restaurantService = RestaurantServiceImplementation.getInstance();
    static OrderService orderService = OrderServiceImplementation.getInstance();

    public static void main(String[] args){
        System.out.println("Food Ordering System is Running\n");

        //Creating Restaurant 1
        Restaurant restaurant1=new Restaurant(Utils.getUUid(),"R1",2,4.5);
        restaurant1.addMenuItem("Veg Biryani",100);
        restaurant1.addMenuItem("Chicken Biryani",150);

        //Creating Restaurant 2
        Restaurant restaurant2=new Restaurant(Utils.getUUid(),"R2",1,4);
        restaurant2.addMenuItem("Idli",10);
        restaurant2.addMenuItem("Dosa Posa",50);
        restaurant2.addMenuItem("Veg Biryani",80);
        restaurant2.addMenuItem("Chicken Biryani",175);

        //Creating Restaurant 3
        Restaurant restaurant3=new Restaurant(Utils.getUUid(),"R3",2,4.9);
        restaurant3.addMenuItem("Idli",15);
        restaurant3.addMenuItem("Dosa",30);
        restaurant3.addMenuItem("Gobi Manchurian",150);
        restaurant3.addMenuItem("Chicken Biryani",175);

        //Onboarding Restaurants
        restaurantService.onboardRestaurant(restaurant1);
        restaurantService.onboardRestaurant(restaurant2);
        restaurantService.onboardRestaurant(restaurant3);

        //Adding new item to restaurant menu
        restaurantService.addOrUpdateMenuItem(restaurant3.getName(),"Chai",10);
        //Updating existing item's price of restaurant menu
        restaurantService.addOrUpdateMenuItem(restaurant2.getName(),"Chicken Biryani",220);

        Map<String, Integer> orderItems = new HashMap<>();
        orderItems.put("Idli", 3);
        orderItems.put("Dosa", 1);

        //Checking a particular restaurant can fulfill the order on not
        restaurantService.canFulfillOrder(restaurant1,orderItems);

        //Placing Order for user Ashwin
        orderService.placeOrder("Ashwin", orderItems, SelectionStrategyType.LOWEST_COST);

        //Placing Order for user Harish
        orderService.placeOrder("Harish", orderItems, SelectionStrategyType.LOWEST_COST);

        //Placing Order for user Ziya
        orderService.placeOrder("Ziya", orderItems, SelectionStrategyType.HIGHEST_RATING);

        //Printing all restaurant data
        List<Restaurant> restaurantList=restaurantService.getAllRestaurants();
        for(Restaurant restaurantData:restaurantList){
            System.out.println("\n"+restaurantData);
        }
    }
}
