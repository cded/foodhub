// package example.foodhub.transaction.service;

// import java.math.BigDecimal;
// import java.util.List;

// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import example.foodhub.transaction.model.Account;
// import example.foodhub.transaction.repository.AccountRepository;

// @Service
// public class TransferService {
// private final AccountRepository accountRepository;

// public TransferService(AccountRepository accountRepository) {
// this.accountRepository = accountRepository;
// }

// @Transactional
// public void transferMoney(long idSender, long idReceiver, BigDecimal amount)
// {
// Account senderAccount = accountRepository.findAccountById(idSender);
// Account receiverAccount = accountRepository.findAccountById(idReceiver);

// BigDecimal senderNewAmount = senderAccount.getAmount().subtract(amount);
// BigDecimal receiverNewAmount = receiverAccount.getAmount().add(amount);

// accountRepository.changeAmount(idSender, senderNewAmount);
// accountRepository.changeAmount(idReceiver, receiverNewAmount);
// }

// public Iterable<Account> getAllAccounts() {
// return accountRepository.findAll();
// }

// public List<Account> getAccountByName(String name) {
// return accountRepository.findAccountsByName(name);
// }
// }
