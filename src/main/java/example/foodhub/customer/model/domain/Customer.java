package example.foodhub.customer.model.domain;

import example.foodhub.auth.model.domain.User;
import jakarta.persistence.*;

@Entity
public class Customer {
    @Id
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name ="userId")
    private User user;

    public Customer(User user) {
        this.user = user;
    }

    private String location;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
