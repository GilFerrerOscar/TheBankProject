package com.oscarisma.thebankproject.Repositories;

import com.oscarisma.thebankproject.Models.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovementRepository extends JpaRepository<Movement,Integer> {
}
