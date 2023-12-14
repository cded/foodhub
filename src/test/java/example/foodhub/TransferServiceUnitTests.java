// package example.foodhub;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.verify;

// import java.math.BigDecimal;
// import java.util.Optional;

// import org.junit.jupiter.api.Test;

// import example.foodhub.transaction.model.Account;
// import example.foodhub.transaction.repository.AccountRepository;
// import example.foodhub.transaction.service.TransferService;
// import jakarta.persistence.criteria.From;

// public class TransferServiceUnitTests {
// @Test
// public void moneyTransferHappyFlow() {
// AccountRepository accountRepository = mock(AccountRepository.class);

// TransferService transferService = new TransferService(accountRepository);

// Account sender = new Account();
// sender.setId(1L);
// sender.setAmount(new BigDecimal(1000));

// Account destination = new Account();
// destination.setId(2L);
// destination.setAmount(new BigDecimal(1000));

// assertEquals(accountRepository.findById(sender.getId()),
// Optional.of(sender));

// assertEquals(accountRepository.findById(destination.getId()),
// Optional.of(destination));

// transferService.transferMoney(
// sender.getId(),
// destination.getId(),
// new BigDecimal(100));

// verify(accountRepository)
// .changeAmount(1, new BigDecimal(900));

// verify(accountRepository)
// .changeAmount(2, new BigDecimal(1100));
// }
// }
