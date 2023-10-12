package ru.aston.banktest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.aston.banktest.adpapter.AccountAdapter;
import ru.aston.banktest.adpapter.BillAdapter;
import ru.aston.banktest.adpapter.HistoryAdapter;
import ru.aston.banktest.core.entity.Account;
import ru.aston.banktest.core.service.AccountService;
import ru.aston.banktest.core.service.BillService;
import ru.aston.banktest.core.service.HistoryService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BankTestApplicationTests {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountAdapter accountAdapter;
    @Autowired
    private BillService billService;
    @Autowired
    private BillAdapter billAdapter;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private HistoryAdapter historyAdapter;


    @Test
    void contextLoads() {
        assertNotNull(accountAdapter);
        assertNotNull(accountService);
        assertNotNull(billAdapter);
        assertNotNull(billService);
        assertNotNull(historyAdapter);
        assertNotNull(historyService);
    }

    @Test
    void DBTest() {
        Account account = accountAdapter.getAccount("test");
        assertNotNull(account);
    }

}
