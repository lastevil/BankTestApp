package ru.aston.banktest.contract.bill.output;

import ru.aston.banktest.core.entity.Account;
import ru.aston.banktest.core.entity.Bill;

public interface BillOutputManager {
    void cerateBill(Account account, String pinCode, String s);

    Bill getBill(String billNumber);

    void update(Bill currentBill);
}
