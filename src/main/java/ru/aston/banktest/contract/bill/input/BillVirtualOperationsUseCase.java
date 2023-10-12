package ru.aston.banktest.contract.bill.input;


import ru.aston.banktest.dto.output.BillOutputDto;

import java.math.BigDecimal;

public interface BillVirtualOperationsUseCase {
    BillOutputDto transfer(String fromScore, String toScore, BigDecimal sum, String pinCode);
}
