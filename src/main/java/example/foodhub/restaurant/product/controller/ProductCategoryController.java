package example.foodhub.restaurant.product.controller;

import example.foodhub.restaurant.product.model.domain.ProductCategory;
import example.foodhub.restaurant.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/product-categories")
public class ProductCategoryController {

    private final ProductService productService;

    @Autowired
    public ProductCategoryController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductCategory> getAllProductCategories() {
        return productService.getAllProductCategories();
    }

    @GetMapping("/{categoryId}")
    public ProductCategory getProductCategoryById(@PathVariable Long categoryId) {
        return productService.getCategoryById(categoryId)
                .orElseThrow(() -> new RuntimeException("ProductCategory not found with id: " + categoryId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ProductCategory> createProductCategory(@RequestBody ProductCategory productCategory)
            throws URISyntaxException {
        // Save the category
        ProductCategory savedCategory = productService.saveProductCategory(productCategory);

        // Build the URI using ServletUriComponentsBuilder
        URI location = new URI("/api/product-categories/" + savedCategory.getId());

        // Return a ResponseEntity with the created category and the location header
        return ResponseEntity.created(location).body(savedCategory);
    }

}
