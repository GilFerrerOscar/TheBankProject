package com.oscarisma.thebankproject.Controllers;

import com.oscarisma.thebankproject.Exceptions.AccountNotFoundException;
import com.oscarisma.thebankproject.Exceptions.UserNotFoundException;
import com.oscarisma.thebankproject.Models.Account;
import com.oscarisma.thebankproject.Models.User;
import com.oscarisma.thebankproject.Repositories.AccountRepository;
import com.oscarisma.thebankproject.Repositories.UserRepository;
import com.oscarisma.thebankproject.Services.AccountDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user/{userId}/accounts")
public class AccountController {

    AccountRepository accountRepository;
    UserRepository userRepository;

    AccountDaoService userService;

    public static void substractAmount(Account account, float amount){
        account.setBalance(account.getBalance()-amount);
    }

    public static void addAmount(Account account, float amount){
        account.setBalance(account.getBalance()+amount);
    }

    @Autowired
    public AccountController(UserRepository userRepository, AccountRepository accountRepository, AccountDaoService userService){
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> retrieveAccountInfo(@PathVariable int userId, @PathVariable int accountId){
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
        Account account = user.getAccounts().stream().filter(acc -> acc.getId().equals(accountId)).findFirst().orElseThrow(() -> new AccountNotFoundException("Account not found with ID:" + accountId));
        return ResponseEntity.ok(account);
    }
    @PostMapping
    public ResponseEntity<Account> createAccount(@PathVariable int userId, @RequestBody Account account){
        User savedUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
        account.setUser(savedUser);
        Account createdAccount = accountRepository.save(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

    @DeleteMapping("/{accountId}")
    public void deleteAccount(@PathVariable int userId, @PathVariable int accountId){
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
        Account account = user.getAccounts().stream().filter(acc -> acc.getId().equals(accountId)).findFirst().orElseThrow(() -> new AccountNotFoundException("Account not found with ID: " + accountId));
        user.getAccounts().remove(account);
        userRepository.save(user);
        accountRepository.delete(account);
    }

    @PutMapping("/{accountId}")
    public Account updateAccount(@PathVariable int accountId, @PathVariable int userId, @RequestBody Account account){
        return userService.updateAccount(userId, accountId, account);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleAccountNotFoundException(AccountNotFoundException e){
        return e.getMessage();
    }



}
