-- Начинаем транзакцию
BEGIN;

-- Проверяем, достаточно ли средств на исходном счете
IF
(SELECT balance FROM accounts WHERE account_number = 'SOURCE_ACCOUNT_NUMBER')
>= AMOUNT THEN

-- Обновляем баланс исходного счета
UPDATE accounts
SET balance = balance - AMOUNT
WHERE account_number = 'SOURCE_ACCOUNT_NUMBER';

-- Обновляем баланс целевого счета
UPDATE accounts
SET balance = balance + AMOUNT
WHERE account_number = 'TARGET_ACCOUNT_NUMBER';

-- Добавляем запись о переводе в таблицу transfers
INSERT INTO transfers (id, client_id, target_client_id, source_account, target_account, amount, message)
VALUES ('TRANSACTION_ID', 'CLIENT_ID', 'TARGET_CLIENT_ID', 'SOURCE_ACCOUNT_NUMBER', 'TARGET_ACCOUNT_NUMBER', AMOUNT,
        'MESSAGE');

ELSE
    -- Если средств недостаточно, можно выбросить ошибку или выполнить другую логику
    RAISE EXCEPTION 'Недостаточно средств на счете';
END IF;

-- Завершаем транзакцию
COMMIT;
