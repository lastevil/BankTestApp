package ru.aston.banktest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.aston.banktest.adpapter.AccountAdapter;
import ru.aston.banktest.adpapter.BillAdapter;
import ru.aston.banktest.adpapter.HistoryAdapter;
import ru.aston.banktest.core.service.AccountService;
import ru.aston.banktest.core.service.BillService;
import ru.aston.banktest.core.service.HistoryService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BankTestApplicationTests {
    @Autowired
    AccountService accountService;
    @Autowired
    AccountAdapter accountAdapter;
    @Autowired
    BillService billService;
    @Autowired
    BillAdapter billAdapter;
    @Autowired
    HistoryService historyService;
    @Autowired
    HistoryAdapter historyAdapter;


    @Test
    void contextLoads() {
        assertNotNull(accountAdapter);
        assertNotNull(accountService);
        assertNotNull(billAdapter);
        assertNotNull(billService);
        assertNotNull(historyAdapter);
        assertNotNull(historyService);
    }

}
