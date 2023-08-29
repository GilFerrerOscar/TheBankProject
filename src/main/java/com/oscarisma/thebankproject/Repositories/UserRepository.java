package com.oscarisma.thebankproject.Repositories;

import com.oscarisma.thebankproject.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

public interface UserRepository extends JpaRepository<User, Integer> {
}
