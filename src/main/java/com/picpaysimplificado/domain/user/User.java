package com.picpaysimplificado.domain.user;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity(name = "users")
@Table(name = "users",
        schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "document",
            unique = true)
    private String document;

    @Column(name = "email",
            unique = true)
    private String email;

    @Column(name = "passwsord")
    private String password;

    @Column(name = "balance")
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private UserType type;

    public User() {}

    public User(UUID id, String firstName, String lastName, String document,
                String email, String password, BigDecimal balance, UserType type) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.document = document;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.type = type;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getDocument() {
        return document;
    }
    public void setDocument(String document) {
        this.document = document;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    public UserType getType() {
        return type;
    }
    public void setType(UserType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof User other)) {
            return false;
        }
        return this.id.equals(other.id);
    }
    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
