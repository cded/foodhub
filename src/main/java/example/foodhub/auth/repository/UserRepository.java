package example.foodhub.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import example.foodhub.auth.model.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(nativeQuery = true, value = "Select id, email, password, role, name from users where email =:email")
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);
}
