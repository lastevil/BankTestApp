package ru.aston.banktest.contract.bill.input;

import java.math.BigDecimal;

public interface BillCashOperationsUseCase {
    void deposite(String billNumber, BigDecimal sum);

    void withdraw(String billNumber, BigDecimal sum, String pinCode);
}
