package com.oscarisma.thebankproject.Repositories;

import com.oscarisma.thebankproject.Models.Account;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
