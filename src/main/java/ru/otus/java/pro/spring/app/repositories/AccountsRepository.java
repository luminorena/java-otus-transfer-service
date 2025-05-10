package ru.otus.java.pro.spring.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.java.pro.spring.app.entities.Account;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Account, String> {
    Optional<Account> findAccountById(String id);

    List<Account> findAllById(String id);

    Optional<Account> findAccountByAccountNumber(String accountNumber);

    Optional<Account> findAccountByAccountNumberAndClientId(String accountNumber, String clientId);


}
