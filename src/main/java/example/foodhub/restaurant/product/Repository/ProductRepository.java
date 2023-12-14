package example.foodhub.restaurant.product.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import example.foodhub.restaurant.product.model.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "Select * From product", nativeQuery = true)
    public List<Product> getAllProducts();
}
