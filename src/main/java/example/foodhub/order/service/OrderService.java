package example.foodhub.order.service;

import example.foodhub.auth.model.domain.User;
import example.foodhub.auth.service.SecurityUserService;
import example.foodhub.customer.model.domain.Customer;
import example.foodhub.customer.service.CustomerService;
import example.foodhub.order.controller.OrderController;
import example.foodhub.order.model.domain.Order;
import example.foodhub.order.model.domain.OrderItem;
import example.foodhub.order.model.domain.OrderStatus;
import example.foodhub.order.model.request.OrderRequest;
import example.foodhub.order.model.request.OrderitemRequest;
import example.foodhub.order.repository.OrderRepository;
import example.foodhub.restaurant.info.model.Restaurant;
import example.foodhub.restaurant.info.service.RestaurantService;
import example.foodhub.restaurant.product.model.domain.Product;
import example.foodhub.restaurant.product.service.ProductService;
import jakarta.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final CustomerService customerService;
    private final RestaurantService restaurantService;
    private final SecurityUserService securityUserService;
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    public OrderService(OrderRepository orderRepository, ProductService productService, CustomerService customerService, RestaurantService restaurantService,
                        SecurityUserService securityUserService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.customerService = customerService;
        this.restaurantService = restaurantService;
        this.securityUserService = securityUserService;
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public String placeOrder(OrderRequest orderRequest, Principal principal) {
        logger.info("Starting business logic for order");
        List<OrderitemRequest> orderItems = orderRequest.getItems();
        validateOrderRequest(orderRequest, orderItems);

        String username = principal.getName();
        User user = securityUserService.getUser(username);
        Customer customer = customerService.getCustomer(user.getId());

        logger.info("Validation executed successfully");

        LocalDateTime timeNow = LocalDateTime.now();
        Optional<Restaurant> restaurant = restaurantService.getRestaurantById(orderRequest.getRestaurantId());
        if(restaurant.isEmpty()) {
            throw new ValidationException("Order request cannot be null.");
        }

        Order order = new Order();
        order.setOrderDate(timeNow);
        order.setCustomer(customer);
        order.setRestaurant(restaurant.get());

        List<OrderItem> createdOrderItems = createItems(orderItems);
        order.setItems(createdOrderItems);

        order.setOrderStatus(OrderStatus.PLACED);

        return "Order placed successfully. Order ID: " + order.getId();
    }

    private void validateOrderRequest(OrderRequest orderRequest, List<OrderitemRequest> orderItems) {
        logger.info("Validate Order request");
        if (orderRequest == null) {
            throw new ValidationException("Order request cannot be null.");
        }

        if (orderItems == null) {
            throw new ValidationException("Order items cannot be null.");
        }

        for (OrderitemRequest orderItem : orderItems) {
            validateOrderItem(orderItem, orderRequest.getRestaurantId());
        }

        logger.info("Order request validated");
    }

    private void validateOrderItem(OrderitemRequest orderItem, Long restaurantId) {
        logger.info("Validate order item {}", orderItem);
        if (orderItem == null) {
            throw new ValidationException("Order item cannot be null.");
        }

        Long productId = orderItem.getProductId();

        // Check if the product with the given ID exists
        if (!productService.existsWithinMenu(productId, restaurantId)) {
            throw new ValidationException("Product validation exception " + productId);
        }

        // Check if quantity is a positive integer
        int quantity = orderItem.getQuantity();
        if (quantity <= 0) {
            throw new ValidationException("Quantity must be a positive integer.");
        }
    }

    private List<OrderItem> createItems(List<OrderitemRequest> orderItems) {
        List<OrderItem> items = new ArrayList<>();
        for (OrderitemRequest orderItemRequest : orderItems) {
            Product product = productService.getProductById(orderItemRequest.getProductId());
            if (product == null) {
                throw new ValidationException("Product not found for ID: " + orderItemRequest.getProductId());
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(orderItemRequest.getQuantity());

            items.add(orderItem);
        }
        return items;
    }


}
