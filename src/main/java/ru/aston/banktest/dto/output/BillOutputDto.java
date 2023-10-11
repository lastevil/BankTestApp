package ru.aston.banktest.dto.output;

import ru.aston.banktest.core.entity.Bill;

import java.math.BigDecimal;

public record BillOutputDto(Long id, String billNumber, BigDecimal balance) {
    public static BillOutputDto create(Bill bill) {
        return new BillOutputDto(bill.getId(), bill.getBillNumber(), bill.getBalance());
    }
}
