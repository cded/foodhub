package example.foodhub.order.service;

import example.foodhub.order.controller.OrderController;
import example.foodhub.order.model.domain.Order;
import example.foodhub.order.model.domain.OrderItem;
import example.foodhub.order.model.request.OrderRequest;
import example.foodhub.order.model.request.OrderitemRequest;
import example.foodhub.order.repository.OrderRepository;
import example.foodhub.restaurant.product.service.ProductService;
import jakarta.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    public OrderService(OrderRepository orderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public String placeOrder(OrderRequest orderRequest) {
        logger.info("Starting business logic for order");
        validateOrderRequest((orderRequest));



        return "order placed";
    }

    private void validateOrderRequest(OrderRequest orderRequest) {
        logger.info("Validate Order request");
        if (orderRequest == null) {
            throw new ValidationException("Order request cannot be null.");
        }

//        validateCustomerExistence(orderRequest.getCustomerId());
        List<OrderitemRequest> orderItems = orderRequest.getItems();

        if (orderItems == null) {
            throw new ValidationException("Order items cannot be null.");
        }

        for (OrderitemRequest orderItem : orderItems) {
            validateOrderItem(orderItem, orderRequest.getRestaurantId());
        }

        logger.info("Order request validated");
    }

//    private void validateCustomerExistence(Long customerId) {
//        if (!customerService.existsById(customerId)) {
//            throw new ValidationException("Customer does not exist with ID: " + customerId);
//        }
//    }

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


}
