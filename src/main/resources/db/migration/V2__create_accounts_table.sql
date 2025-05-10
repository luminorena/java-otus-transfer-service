create table accounts
(
    id             varchar(36) primary key,
    account_number varchar(12),
    client_id      varchar(12),
    balance        int,
    is_blocked     bool
);

insert into accounts(id, account_number, client_id, balance, is_blocked)
values ('2dfe9ca8-13e7-4c57-9b2b-2020296de2d9', '000000000001', '1000000001', 200, false),
       ('1603317c-d90e-427a-bcb9-ee9fffc1b6c9', '000000000002', '1000000002', 500, false);