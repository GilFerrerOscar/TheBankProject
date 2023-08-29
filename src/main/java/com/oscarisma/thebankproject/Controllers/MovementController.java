package com.oscarisma.thebankproject.Controllers;

import com.oscarisma.thebankproject.Exceptions.AccountNotFoundException;
import com.oscarisma.thebankproject.Exceptions.UserNotFoundException;
import com.oscarisma.thebankproject.Models.Account;
import com.oscarisma.thebankproject.Models.Movement;
import com.oscarisma.thebankproject.Models.User;
import com.oscarisma.thebankproject.Repositories.AccountRepository;
import com.oscarisma.thebankproject.Repositories.MovementRepository;
import com.oscarisma.thebankproject.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/user/{userID}/account/{accountID}/movements")
public class MovementController {

    UserRepository userRepository;
    AccountRepository accountRepository;
    MovementRepository movementRepository;

    @Autowired
    public MovementController(UserRepository userRepository, AccountRepository accountRepository, MovementRepository movementRepository){
        this.movementRepository = movementRepository;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/{receiverId}")
    public ResponseEntity<Movement> createMovement(@PathVariable int accountID, @PathVariable int receiverId, @RequestBody Movement movement){
         Account senderAccount = accountRepository.findById(accountID).orElseThrow(() -> new AccountNotFoundException("Sender account not found with ID: " + accountID));
         Account receiverAccount = accountRepository.findById(receiverId).orElseThrow(() -> new AccountNotFoundException("Receiver account not found with ID: " + receiverId));
         List<Account> accounts = new ArrayList<Account>();
         accounts.add(senderAccount);
         accounts.add(receiverAccount);
         movement.setAccounts(accounts);
         AccountController.addAmount(receiverAccount, movement.getAmount());
         AccountController.substractAmount(senderAccount, movement.getAmount());
         senderAccount.getMovements().add(movement);
         receiverAccount.getMovements().add(movement);
         Movement savedMovement = movementRepository.save(movement);
         accountRepository.save(senderAccount);
         accountRepository.save(receiverAccount);
         URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                 .path("/{id}").buildAndExpand(savedMovement.getId()).toUri();
         return ResponseEntity.created(location).build();
    }

    @GetMapping("/{movementId}")
    public void retrieveMovementInfo(@PathVariable int movementId){
        movementRepository.findById(movementId);
    }






}
