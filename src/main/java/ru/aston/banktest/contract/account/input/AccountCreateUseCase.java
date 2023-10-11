package ru.aston.banktest.contract.account.input;

public interface AccountCreateUseCase {
    void createAccount(String username, String pinCode);
}
