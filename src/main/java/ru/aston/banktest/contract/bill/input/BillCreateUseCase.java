package ru.aston.banktest.contract.bill.input;

import ru.aston.banktest.core.entity.Account;

public interface BillCreateUseCase {
    void createBill(Account account, String pinCode);
}
