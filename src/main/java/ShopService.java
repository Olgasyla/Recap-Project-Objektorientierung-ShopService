import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();
    private List<Order> orders = new ArrayList<>();
    private OrderStatus OrderStatus;

    public Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Product productToOrder = productRepo.getProductById(productId);
            if (productToOrder == null) {
                System.out.println("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
                return null;
            }
            products.add(productToOrder);
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products,OrderStatus.IN_DELIVERY);

        return orderRepo.addOrder(newOrder);
    }
    public void getOrderByStatus(OrderStatus status) {
        orders.stream()
                .filter(orders -> orders.getStatus().equals(status))
                .collect(Collectors.toList());

    }
}
