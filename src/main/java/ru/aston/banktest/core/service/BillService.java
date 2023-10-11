package ru.aston.banktest.core.service;

import jakarta.transaction.Transactional;
import ru.aston.banktest.contract.bill.input.BillCashOperationsUseCase;
import ru.aston.banktest.contract.bill.input.BillCreateUseCase;
import ru.aston.banktest.contract.bill.input.BillVirtualOperationsUseCase;
import ru.aston.banktest.contract.bill.output.BillOutputManager;
import ru.aston.banktest.contract.history.input.SaveOperationUseCase;
import ru.aston.banktest.core.entity.Account;
import ru.aston.banktest.core.entity.Actions;
import ru.aston.banktest.core.entity.Bill;
import ru.aston.banktest.exceptions.LowBalanceException;
import ru.aston.banktest.exceptions.ValidationException;

import java.math.BigDecimal;
import java.util.Random;

public class BillService implements BillCreateUseCase, BillCashOperationsUseCase, BillVirtualOperationsUseCase {
    private final BillOutputManager billOutputManager;
    private final SaveOperationUseCase saveOperationUseCase;
    private final Random rn = new Random();

    public BillService(BillOutputManager billOutputManager, SaveOperationUseCase saveOperationUseCase) {
        this.billOutputManager = billOutputManager;
        this.saveOperationUseCase = saveOperationUseCase;
    }

    private String billNumberGenerator() {
        int minimum = 100_000_000;
        int maximum = 999_999_998;
        int randomNum1 = rn.nextInt(maximum - minimum + 1) + minimum;
        int randomNum2 = rn.nextInt(maximum - minimum + 1) + minimum;
        return String.valueOf(randomNum1) + randomNum2;
    }

    @Override
    public void createBill(Account account, String pinCode) {
        billOutputManager.cerateBill(account, pinCode, billNumberGenerator());
    }

    @Override
    @Transactional
    public void deposite(String billNumber, BigDecimal sum) {
        Bill currentBill = billOutputManager.getBill(billNumber);
        BigDecimal result = currentBill.getBalance().add(sum);
        currentBill.setBalance(result);
        billOutputManager.update(currentBill);
        saveOperationUseCase.saveDipositeHistory(Actions.DEPOSITE, currentBill, sum);
    }

    @Override
    @Transactional
    public void withdraw(String billNumber, BigDecimal sum, String pinCode) {
        Bill currentBill = billOutputManager.getBill(billNumber);
        if (!currentBill.validatePin(pinCode)) {
            throw new ValidationException("pin is not correct to this bill");
        }
        BigDecimal currentBalance = currentBill.getBalance();
        if (currentBalance.compareTo(sum)<0){
            throw new LowBalanceException("not enough money");
        }
        BigDecimal result = currentBalance.subtract(sum);
        currentBill.setBalance(result);
        billOutputManager.update(currentBill);
        saveOperationUseCase.saveWithdrawHistory(Actions.WITHDRAW, currentBill, sum);
    }

    @Transactional
    @Override
    public void transfer(String fromBill, String toBill, BigDecimal sum, String pinCode) {
        Bill from = billOutputManager.getBill(fromBill);
        Bill to = billOutputManager.getBill(toBill);
        if (!from.validatePin(pinCode)) {
            throw new ValidationException("pin is not correct to this bill");
        }
        BigDecimal fromBalanceBalance = from.getBalance();
        if (fromBalanceBalance.compareTo(sum)<0){
            throw new LowBalanceException("not enough money");
        }
        BigDecimal result = fromBalanceBalance.subtract(sum);
        from.setBalance(result);
        billOutputManager.update(from);
        result = to.getBalance().add(sum);
        to.setBalance(result);
        billOutputManager.update(to);
        saveOperationUseCase.saveTransferHistory(Actions.TRNSFER, from,to, sum);
    }
}
