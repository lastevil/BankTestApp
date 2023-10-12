package ru.aston.banktest.contract.bill.input;

import ru.aston.banktest.dto.output.BillOutputDto;

import java.math.BigDecimal;

public interface BillCashOperationsUseCase {
    BillOutputDto deposite(String billNumber, BigDecimal sum);

    BillOutputDto withdraw(String billNumber, BigDecimal sum, String pinCode);
}
