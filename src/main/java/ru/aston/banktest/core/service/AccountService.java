package ru.aston.banktest.core.service;

import ru.aston.banktest.contract.account.input.AccountCreateUseCase;
import ru.aston.banktest.contract.account.input.AccountGetUseCase;
import ru.aston.banktest.contract.account.output.AccountOutputManager;
import ru.aston.banktest.contract.bill.input.BillCreateUseCase;
import ru.aston.banktest.core.entity.Account;
import ru.aston.banktest.dto.output.AccountOutputDto;
import ru.aston.banktest.exceptions.BillCreateException;

public class AccountService implements AccountCreateUseCase, AccountGetUseCase {
    private final AccountOutputManager accountOutputManager;
    private final BillCreateUseCase billCreateUseCase;

    public AccountService(AccountOutputManager accountOutputManager, BillCreateUseCase billCreateUseCase) {
        this.accountOutputManager = accountOutputManager;
        this.billCreateUseCase = billCreateUseCase;
    }

    @Override
    public void createAccount(String username, String pinCode) {
        Account account = accountOutputManager.createAccount(username);
        account.getBillList().forEach(bill -> {
            if (bill.validatePin(pinCode))
                throw new BillCreateException("Bill with this pin is exist");
        });
        billCreateUseCase.createBill(account, pinCode);
    }


    @Override
    public AccountOutputDto getAccountInfo(String username) {
        return AccountOutputDto.fromEntityToDto(accountOutputManager.getAccount(username));
    }
}
