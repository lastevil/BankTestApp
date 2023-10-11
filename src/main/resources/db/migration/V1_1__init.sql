CREATE TABLE IF NOT EXISTS accounts
(
    id         bigserial,
    username   varchar(100),
    created_at timestamp with time zone,
    primary key (id)
);
CREATE TABLE IF NOT EXISTS bills
(
    id          bigserial,
    account_id  bigserial    not null,
    pin_code    varchar(10)  not null,
    bill_number varchar(50) not null unique,
    balance     bigint,
    created_at  timestamp with time zone,
    primary key (id),
    foreign key (account_id) references accounts (id)
);

CREATE TABLE IF NOT EXISTS history
(
    id         bigserial,
    action     varchar   not null,
    from_bill  bigserial,
    to_bill    bigserial,
    created_at timestamp with time zone not null,
    amount     bigint,
    primary key (id),
    foreign key (from_bill) references bills (id),
    foreign key (to_bill) references bills (id)
);