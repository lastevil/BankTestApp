package ru.aston.banktest.contract.bill.input;


import java.math.BigDecimal;

public interface BillVirtualOperationsUseCase {
    void transfer(String fromScore, String toScore, BigDecimal sum, String pinCode);
}
