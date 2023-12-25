package example.foodhub.employee.repository;

import example.foodhub.employee.model.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
