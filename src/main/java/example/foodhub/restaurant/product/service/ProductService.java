package example.foodhub.restaurant.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import example.foodhub.restaurant.product.Repository.CategoryRepository;
import example.foodhub.restaurant.product.Repository.ProductRepository;
import example.foodhub.restaurant.product.model.domain.Product;
import example.foodhub.restaurant.product.model.domain.ProductCategory;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getProducts() {
        return productRepository.getAllProducts();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product addProduct(Product p) {
        return productRepository.save(p);
    }

    public Optional<ProductCategory> getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    public List<ProductCategory> getAllProductCategories() {
        return categoryRepository.findAll();
    }

    public ProductCategory saveProductCategory(ProductCategory productCategory) {
        // Add any validation or business logic before saving if needed
        return categoryRepository.save(productCategory);
    }

}