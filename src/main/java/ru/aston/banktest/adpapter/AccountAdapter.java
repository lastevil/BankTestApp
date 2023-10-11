package ru.aston.banktest.adpapter;

import ru.aston.banktest.contract.account.output.AccountOutputManager;
import ru.aston.banktest.core.entity.Account;
import ru.aston.banktest.core.repository.AccountRepository;
import ru.aston.banktest.exceptions.EntityNotFoundException;

import java.util.Optional;

public class AccountAdapter implements AccountOutputManager {

    private final AccountRepository repository;

    public AccountAdapter(AccountRepository accountRepository) {
        this.repository = accountRepository;
    }

    @Override
    public Account createAccount(String username) {
        Optional<Account> account = repository.findByUsername(username);
        return account.orElseGet(() -> repository.save(new Account(username)));
    }

    @Override
    public Account getAccount(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User " + username + " not found."));
    }
}
