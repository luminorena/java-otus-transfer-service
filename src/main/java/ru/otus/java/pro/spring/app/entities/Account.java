package ru.otus.java.pro.spring.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @Column(name = "id")
    String id;

    @Column(name = "account_number")
    String accountNumber;

    @Column(name = "client_id")
    String clientId;

    @Column(name = "balance")
    int balance;

    @Column(name = "is_blocked")
    boolean isBlocked;
}
