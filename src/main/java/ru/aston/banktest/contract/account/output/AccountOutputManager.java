package ru.aston.banktest.contract.account.output;


import ru.aston.banktest.core.entity.Account;

public interface AccountOutputManager {
    Account createAccount(String username);
    Account getAccount(String username);
}
