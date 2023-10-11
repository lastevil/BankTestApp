INSERT INTO accounts(username, created_at)
values ('test', CURRENT_TIMESTAMP(0));

INSERT INTO bills(account_id, pin_code, bill_number, balance,created_at)
VALUES (1, 1234, 998140924821401840, 100, CURRENT_TIMESTAMP(0))