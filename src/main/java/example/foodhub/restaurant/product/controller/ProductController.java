package example.foodhub.restaurant.product.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import example.foodhub.restaurant.product.model.domain.Product;
import example.foodhub.restaurant.product.model.domain.ProductCategory;
import example.foodhub.restaurant.product.model.request.CreateProductRequest;
import example.foodhub.restaurant.product.service.ProductService;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Product> product(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/products")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Product>> products() {
        List<Product> products = productService.getProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/product")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> addProduct(@RequestBody CreateProductRequest createProductRequest) {
        Product newProduct = new Product();
        newProduct.setName(createProductRequest.getName());
        newProduct.setPrice(createProductRequest.getPrice());
        newProduct.setDescription(createProductRequest.getDescription());

        ProductCategory category = productService.getCategoryById(createProductRequest.getCategory())
                .orElseThrow(() -> new RuntimeException(
                        "ProductCategory not found with id: " + createProductRequest.getCategory()));
        newProduct.setCategory(category);

        Product savedProduct = productService.addProduct(newProduct);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProduct.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(savedProduct);
    }
}
