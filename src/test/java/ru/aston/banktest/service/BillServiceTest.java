package ru.aston.banktest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.aston.banktest.adpapter.BillAdapter;
import ru.aston.banktest.contract.history.input.SaveOperationUseCase;
import ru.aston.banktest.core.entity.Account;
import ru.aston.banktest.core.entity.Bill;
import ru.aston.banktest.core.repository.BillRepository;
import ru.aston.banktest.core.service.BillService;
import ru.aston.banktest.exceptions.EntityNotFoundException;
import ru.aston.banktest.exceptions.LowBalanceException;
import ru.aston.banktest.exceptions.ValidationException;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {BillAdapter.class, BillService.class})
class BillServiceTest {
    @Autowired
    BillService billService;
    @MockBean
    BillRepository repository;
    @MockBean
    SaveOperationUseCase saveOperationUseCase;

    @BeforeEach
    void before() {
        Account account = new Account();
        account.setId(1L);
        account.setUsername("test");
        Bill first = new Bill();
        first.setBillNumber("1111111111111111111");
        first.setBalance(BigDecimal.TEN);
        first.setId(1L);
        first.setPinCode("1111");
        first.setAccount(account);
        Mockito.when(repository.findByBillNumber("1111111111111111111")).thenReturn(Optional.of(first));
        Mockito.when(repository.findByBillNumber("33333333")).thenReturn(Optional.empty());
    }


    @Test
    void depositeTest() {
        var bill = billService.deposite("1111111111111111111", BigDecimal.TEN);
        assertNotNull(bill);
        assertEquals(BigDecimal.valueOf(20), bill.balance());
    }

    @Test
    void withdrawTest() {
        var bill = billService.withdraw("1111111111111111111", BigDecimal.TEN,"1111");
        assertNotNull(bill);
        assertEquals(BigDecimal.ZERO, bill.balance());
    }
    @Test
    void withdrawPinErrorTest() {
        ValidationException exception = assertThrows(ValidationException.class,
                () -> billService.withdraw("1111111111111111111", BigDecimal.TEN, "7777"));

        assertEquals("Pin is not correct to this bill", exception.getMessage());
    }

    @Test
    void withdrawCashErrorTest() {
        LowBalanceException exception = assertThrows(LowBalanceException.class,
                () -> billService.withdraw("1111111111111111111", BigDecimal.valueOf(20), "1111"));

        assertEquals("Not enough money", exception.getMessage());
    }

    @Test
    void billNotFoundException() {
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> billService.withdraw("33333333", BigDecimal.TEN, "1111"));
        assertEquals("BillNumber not found", exception.getMessage());
    }
}
