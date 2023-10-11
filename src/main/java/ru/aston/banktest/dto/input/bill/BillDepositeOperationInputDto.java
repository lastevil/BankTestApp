package ru.aston.banktest.dto.input.bill;

import java.math.BigDecimal;

public record BillDepositeOperationInputDto(String billNumber, BigDecimal sum){
}
