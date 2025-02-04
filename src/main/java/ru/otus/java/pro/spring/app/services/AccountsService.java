package ru.otus.java.pro.spring.app.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.java.pro.spring.app.entities.Account;
import ru.otus.java.pro.spring.app.repositories.AccountsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountsService {

    private final AccountsRepository accountsRepository;

    public Optional<Account> getAccountById(String id) {
        return accountsRepository.findAccountById(id);
    }

    public List<Account> getAllAccountsById(String id) {
        return accountsRepository.findAllById(id);
    }
}
