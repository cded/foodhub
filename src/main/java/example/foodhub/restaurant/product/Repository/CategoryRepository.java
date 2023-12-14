package example.foodhub.restaurant.product.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import example.foodhub.restaurant.product.model.domain.ProductCategory;

public interface CategoryRepository extends JpaRepository<ProductCategory, Long> {

}
