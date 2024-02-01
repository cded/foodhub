package example.foodhub.customer.model.domain;

import example.foodhub.address.domain.Address;
import example.foodhub.auth.model.domain.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Customer {
    @Id
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name ="userId")
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> addresses;

    public Customer(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
