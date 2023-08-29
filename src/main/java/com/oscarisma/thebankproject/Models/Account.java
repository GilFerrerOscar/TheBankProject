package com.oscarisma.thebankproject.Models;

import com.fasterxml.jackson.annotation.*;
import com.oscarisma.thebankproject.Exceptions.UserNotFoundException;
import com.oscarisma.thebankproject.Repositories.UserRepository;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private Integer Id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user.id")
    private User user;

    @Column
    private String bank;

    @Column
    private float balance;

    @JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class, property="account_id")
    @ManyToMany
    @JoinTable(name = "account_movement", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "movement_id"))
    private List<Movement> movements;

    public Account() {}

    public Account(Integer id, User user, String bank, float balance) {
        Id = id;
        this.user = user;
        this.bank = bank;
        this.balance = balance;
        this.movements = new ArrayList<>();
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public List<Movement> getMovements() {
        return movements;
    }

    public void setMovements(List<Movement> movements) {
        this.movements = movements;
    }

    @Override
    public String toString() {
        return "Account{" +
                "Id=" + Id +
                ", user=" + user +
                ", bank='" + bank + '\'' +
                ", movements=" + movements +
                '}';
    }
}
