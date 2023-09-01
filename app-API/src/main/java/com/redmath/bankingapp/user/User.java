package com.redmath.bankingapp.user;

import com.redmath.bankingapp.balance.Balance;
import com.redmath.bankingapp.transaction.Transaction;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "users")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "user_id")
    private Long id;
    private String userName;
    private String password;
    private String Email;
    private String address;
    private String roles;

//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    private Balance balance;
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Transaction> transaction;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() { return Email; }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoles() {
        return roles;
    }

    public void setRole(String role) {
        this.roles = role;
    }
}
