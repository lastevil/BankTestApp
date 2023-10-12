package ru.aston.banktest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.aston.banktest.adpapter.AccountAdapter;
import ru.aston.banktest.contract.bill.input.BillCreateUseCase;
import ru.aston.banktest.core.entity.Account;
import ru.aston.banktest.core.entity.Bill;
import ru.aston.banktest.core.repository.AccountRepository;
import ru.aston.banktest.core.service.AccountService;
import ru.aston.banktest.exceptions.BillCreateException;
import ru.aston.banktest.exceptions.EntityNotFoundException;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {AccountService.class, AccountAdapter.class})
class AccountServiceTest {
    @Autowired
    private AccountService service;
    @MockBean
    private AccountRepository repository;
    @MockBean
    private BillCreateUseCase billCreateUseCase;

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
        Bill second = new Bill();
        second.setBillNumber("2222222222222222222");
        second.setBalance(BigDecimal.TEN);
        second.setId(2L);
        second.setPinCode("2222");
        second.setAccount(account);
        account.setBillList(Set.of(first,second));
        Mockito.when(repository.findByUsername("test")).thenReturn(Optional.of(account));
        Mockito.when(repository.findByUsername("user")).thenReturn(Optional.empty());
    }

    @Test
    void getAccountInfoTest() {
        var info = service.getAccountInfo("test");
        assertNotNull(info);
        assertEquals(info.username(), "test");
    }

    @Test
    void getAccountInfoThrowTest() {
        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class,
                () -> service.getAccountInfo("user"));
        assertEquals("User user not found.", thrown.getMessage());
    }

    @Test
    void createAccountErrorTest() {
        BillCreateException exception = assertThrows(BillCreateException.class,
                () -> service.createAccount("test", "1111"));
        assertEquals("Bill with this pin is exist", exception.getMessage());
    }
}
