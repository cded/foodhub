// package example.foodhub.transaction.repository;

// import java.math.BigDecimal;
// import java.util.List;

// import org.springframework.data.jpa.repository.Modifying;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.CrudRepository;

// import example.foodhub.transaction.model.Account;

// public interface AccountRepository extends CrudRepository<Account, Long> {

// @Query(value = "SELECT * FROM account WHERE name = :name", nativeQuery =
// true)
// List<Account> findAccountsByName(String name);

// @Query(value = "SELECT * FROM account WHERE id = :id", nativeQuery = true)
// Account findAccountById(Long id);

// @Modifying
// @Query(value = "UPDATE account SET amount = :amount WHERE id = :id",
// nativeQuery = true)
// void changeAmount(long id, BigDecimal amount);
// }
