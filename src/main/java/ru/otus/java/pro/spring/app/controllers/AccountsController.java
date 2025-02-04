package ru.otus.java.pro.spring.app.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.java.pro.spring.app.dtos.AccountDto;
import ru.otus.java.pro.spring.app.dtos.AccountPageDto;
import ru.otus.java.pro.spring.app.entities.Account;
import ru.otus.java.pro.spring.app.exceptions_handling.ResourceNotFoundException;
import ru.otus.java.pro.spring.app.services.AccountsService;

import java.util.function.Function;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountsController {
    private static final Function<Account, AccountDto> ENTITY_TO_DTO_MAPPING = t -> new AccountDto(t.getId(), t.getAccountNumber(),
            t.getClientId(), t.getBalance(), t.isBlocked());
    private final AccountsService accountsService;

    @GetMapping("/{id}")
    public AccountDto getAccountById(@RequestHeader(name = "client-id") String clientId, @PathVariable String id) {
        return ENTITY_TO_DTO_MAPPING.apply(accountsService.getAccountById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Счёт не найден")));
    }


    @GetMapping
    public AccountPageDto getAllAccounts(@RequestHeader(name = "client-id") String clientId) {
        return new AccountPageDto(
                accountsService.getAllAccountsById(clientId)
                        .stream()
                        .map(ENTITY_TO_DTO_MAPPING)
                        .collect(Collectors.toList()));
    }


}
