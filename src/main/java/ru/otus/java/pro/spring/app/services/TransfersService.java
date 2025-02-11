package ru.otus.java.pro.spring.app.services;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.java.pro.spring.app.dtos.ExecuteTransferDtoRq;
import ru.otus.java.pro.spring.app.entities.Account;
import ru.otus.java.pro.spring.app.entities.Transfer;
import ru.otus.java.pro.spring.app.exceptions_handling.BusinessLogicException;
import ru.otus.java.pro.spring.app.exceptions_handling.BusinessLogicFieldError;
import ru.otus.java.pro.spring.app.exceptions_handling.ValidationException;
import ru.otus.java.pro.spring.app.exceptions_handling.ValidationFieldError;
import ru.otus.java.pro.spring.app.repositories.AccountsRepository;
import ru.otus.java.pro.spring.app.repositories.TransfersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransfersService {
    private final TransfersRepository transfersRepository;
    private final AccountsRepository accountsRepository;


    public Optional<Transfer> getTransferById(String id, String clientId) {
        return transfersRepository.findByIdAndClientId(id, clientId);
    }

    public List<Transfer> getAllTransfers(String clientId) {
        return transfersRepository.findAllByClientIdOrTargetClientId(clientId);
    }



    @Transactional
    public Transfer execute(String clientId, ExecuteTransferDtoRq executeTransferDtoRq) {
        validateExecuteTransferDtoRq(executeTransferDtoRq);

        Account sourceAccount = accountsRepository.findAccountByAccountNumberAndClientId(
                executeTransferDtoRq.sourceAccount(), clientId)
                .orElseThrow(() -> new BusinessLogicException(
                        "SOURCE_ACCOUNT_NOT_FOUND", "Счет отправителя не найден"));

        Account targetAccount = accountsRepository.findAccountByAccountNumber(executeTransferDtoRq.targetAccount())
                .orElseThrow(() -> new BusinessLogicException(
                        "TARGET_ACCOUNT_NOT_FOUND", "Счет получателя не найден"));

        validateBusinessLogicErrorDto(sourceAccount, targetAccount, executeTransferDtoRq.amount());

        return performTransfer(sourceAccount, targetAccount, executeTransferDtoRq.amount());


    }

    private Transfer performTransfer(Account sourceAccount, Account targetAccount, int amount) {
        sourceAccount.setBalance(sourceAccount.getBalance() - amount);
        targetAccount.setBalance(targetAccount.getBalance() + amount);

        accountsRepository.save(sourceAccount);
        accountsRepository.save(targetAccount);

        Transfer transfer = new Transfer();
        transfer.setId(UUID.randomUUID().toString());
        transfer.setClientId(sourceAccount.getClientId());
        transfer.setTargetClientId(targetAccount.getClientId());
        transfer.setSourceAccount(sourceAccount.getAccountNumber());
        transfer.setTargetAccount(targetAccount.getAccountNumber());
        transfer.setAmount(amount);
        transfer.setMessage("Перевод выполнен успешно");
        transfersRepository.save(transfer);
        return transfer;

    }


    private void validateExecuteTransferDtoRq(ExecuteTransferDtoRq executeTransferDtoRq) {
        List<ValidationFieldError> errors = new ArrayList<>();
        if (executeTransferDtoRq.sourceAccount().length() != 12) {
            errors.add(new ValidationFieldError("sourceAccount", "Длина поля счет отправителя должна составлять 12 символов"));
        }
        if (executeTransferDtoRq.targetAccount().length() != 12) {
            errors.add(new ValidationFieldError("targetAccount", "Длина поля счет получателя должна составлять 12 символов"));
        }
        if (executeTransferDtoRq.amount() <= 0) {
            errors.add(new ValidationFieldError("amount", "Сумма перевода должна быть больше 0"));
        }
        if (!errors.isEmpty()) {
            throw new ValidationException("EXECUTE_TRANSFER_VALIDATION_ERROR", "Проблемы заполнения полей перевода", errors);
        }
    }

    private void validateBusinessLogicErrorDto(Account senderAccount, Account receiverAccount, int amountToTransfer) {
        List<BusinessLogicFieldError> errors = new ArrayList<>();
        if (senderAccount.isBlocked()) {
            errors.add(new BusinessLogicFieldError("isBlocked", "Счёт отправителя заблокирован!"));
        }
        if (receiverAccount.isBlocked()) {
            errors.add(new BusinessLogicFieldError("isBlocked", "Счёт получателя заблокирован!"));
        }
//        if (!senderAccount.getClientId().equals(senderClientId)) {
//            errors.add(new BusinessLogicFieldError("clientId", "Неверныо указан отправитель"));
//        }
        if (senderAccount.getBalance() < amountToTransfer) {
            errors.add(new BusinessLogicFieldError("balance", "На счету отправителя не хватает средств для перевода"));
        }
        if (!errors.isEmpty()) {
            throw new BusinessLogicException("BUSINESS_LOGIC_EXCEPTION", "Перевод не может быть выполнен", errors);
        }
    }
}
