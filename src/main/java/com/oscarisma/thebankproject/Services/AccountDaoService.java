package com.oscarisma.thebankproject.Services;

import com.oscarisma.thebankproject.Exceptions.AccountNotFoundException;
import com.oscarisma.thebankproject.Exceptions.UserNotFoundException;
import com.oscarisma.thebankproject.Models.Account;
import com.oscarisma.thebankproject.Models.User;
import com.oscarisma.thebankproject.Repositories.AccountRepository;
import com.oscarisma.thebankproject.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountDaoService {

    AccountRepository accountRepository;
    UserRepository userRepository;

    @Autowired
    public AccountDaoService(AccountRepository accountRepository, UserRepository userRepository){
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }


    public Account updateAccount(int userId, int accountId, Account account) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
        Account existingAccount = user.getAccounts().stream().filter(acc -> acc.getId().equals(accountId)).findFirst().orElseThrow(() -> new AccountNotFoundException("Account not found with ID: " + accountId));
        user.getAccounts().remove(existingAccount);
        accountRepository.delete(existingAccount);
        existingAccount.setBalance(account.getBalance());
        existingAccount.setBank(account.getBank());
        user.getAccounts().add(existingAccount);
        userRepository.save(user);
        accountRepository.save(existingAccount);
        return existingAccount;
    }
}
