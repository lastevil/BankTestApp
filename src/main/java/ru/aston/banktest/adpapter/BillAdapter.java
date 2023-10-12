package ru.aston.banktest.adpapter;

import ru.aston.banktest.contract.bill.output.BillOutputManager;
import ru.aston.banktest.core.entity.Account;
import ru.aston.banktest.core.entity.Bill;
import ru.aston.banktest.core.repository.BillRepository;
import ru.aston.banktest.exceptions.EntityNotFoundException;

public class BillAdapter implements BillOutputManager {
    private final BillRepository repository;

    public BillAdapter(BillRepository repository) {
        this.repository = repository;
    }

    @Override
    public void cerateBill(Account account, String pinCode, String billNumber) {
        repository.save(new Bill(account, pinCode, billNumber));
    }

    @Override
    public Bill getBill(String billNumber) {
        return repository.findByBillNumber(billNumber)
                .orElseThrow(() -> new EntityNotFoundException("BillNumber not found"));
    }

    @Override
    public void update(Bill currentBill) {
        repository.save(currentBill);
    }
}
