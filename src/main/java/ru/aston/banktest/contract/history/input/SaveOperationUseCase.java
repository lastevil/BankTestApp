package ru.aston.banktest.contract.history.input;

import ru.aston.banktest.core.entity.Actions;
import ru.aston.banktest.core.entity.Bill;

import java.math.BigDecimal;

public interface SaveOperationUseCase {
    void saveDipositeHistory(Actions actions, Bill toBillNumber, BigDecimal sum);

    void saveTransferHistory(Actions actions, Bill from, Bill to, BigDecimal sum);

    void saveWithdrawHistory(Actions actions, Bill fromBillNumber, BigDecimal sum);
}
