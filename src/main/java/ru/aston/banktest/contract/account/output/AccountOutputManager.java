package ru.aston.banktest.contract.account.output;


import org.springframework.data.domain.Page;
import ru.aston.banktest.core.entity.Account;

public interface AccountOutputManager {
    Account createAccount(String username);
    Account getAccount(String username);

    Page<Account> getAllAccount(int page, int elementsCount);

    void removeAll();
}
