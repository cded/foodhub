package example.foodhub.restaurant.info.repository;

import example.foodhub.restaurant.info.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
