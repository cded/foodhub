package example.foodhub.customer.service;

import example.foodhub.customer.model.domain.Customer;
import example.foodhub.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).get();
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
}
