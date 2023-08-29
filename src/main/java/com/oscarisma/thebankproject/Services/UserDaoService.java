package com.oscarisma.thebankproject.Services;

import com.oscarisma.thebankproject.Models.User;
import com.oscarisma.thebankproject.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

    @Autowired
    public UserRepository repository;

    public User updateUser(int id, User updatedUser){
        User existingUser = repository.findById(id).get();
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());
        return repository.save(existingUser);

    }

}
