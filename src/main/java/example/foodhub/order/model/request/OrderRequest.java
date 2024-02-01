package example.foodhub.order.model.request;

import java.util.List;

public class OrderRequest {
    private Long restaurantId;
    private Long customerId;
    private List<OrderitemRequest> items;

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<OrderitemRequest> getItems() {
        return items;
    }

    public void setItems(List<OrderitemRequest> items) {
        this.items = items;
    }
}
