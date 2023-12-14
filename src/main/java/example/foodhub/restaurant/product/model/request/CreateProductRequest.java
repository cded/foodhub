package example.foodhub.restaurant.product.model.request;

public class CreateProductRequest {
    private String name;
    private String description;
    private double price;
    private Long category;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getCategory() {
        return this.category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

}
