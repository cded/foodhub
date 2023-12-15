package example.foodhub.employee.model.domain;

import example.foodhub.auth.model.domain.User;
import jakarta.persistence.Entity;

@Entity
public class Employee extends User {
    // Additional employee-specific fields, if any
}