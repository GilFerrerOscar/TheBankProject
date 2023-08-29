package com.oscarisma.thebankproject.Controllers;

import com.oscarisma.thebankproject.Exceptions.UserNotFoundException;
import com.oscarisma.thebankproject.Models.User;
import com.oscarisma.thebankproject.Repositories.UserRepository;
import com.oscarisma.thebankproject.Services.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    UserRepository repository;
    UserDaoService service;

    @Autowired
    public UserController(UserRepository repository, UserDaoService service){
        this.repository = repository;
        this.service = service;
    }

    @GetMapping("/api/users")
    public List<User> retrieveAllUsers(){
        return repository.findAll();
    }

    @GetMapping("/api/user/{id}")
    public User findUserbyId(@PathVariable int id){
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with ID:" + id));
    }

    @PostMapping("/api/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = repository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/api/user/{id}")
    public void deleteUser(@PathVariable int id){
        repository.delete(repository.findById(id).get());
    }

    @PutMapping("/api/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user){
        return service.updateUser(id, user);
    }

    /*@ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleUserNotFoundException(UserNotFoundException e){
        return e.getMessage();
    }*/


}
