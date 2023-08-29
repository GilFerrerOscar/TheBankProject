package com.oscarisma.thebankproject.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.oscarisma.thebankproject.Exceptions.AccountNotFoundException;
import com.oscarisma.thebankproject.Repositories.AccountRepository;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Movement {

    @Id
    @GeneratedValue
    private Integer id;

    @JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class,property="movement_id")
    @ManyToMany(mappedBy = "movements")
    private List<Account> accounts;

    @Column
    private float amount;

    public Movement(){}

    public Movement(Integer id, float amount) {
        this.id = id;
        this.accounts = new ArrayList<Account>();
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts){
        this.accounts = accounts;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
