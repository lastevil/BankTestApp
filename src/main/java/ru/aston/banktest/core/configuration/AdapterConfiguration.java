package ru.aston.banktest.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.aston.banktest.adpapter.AccountAdapter;
import ru.aston.banktest.adpapter.BillAdapter;
import ru.aston.banktest.adpapter.HistoryAdapter;
import ru.aston.banktest.core.repository.AccountRepository;
import ru.aston.banktest.core.repository.BillRepository;
import ru.aston.banktest.core.repository.HistoryRepository;

@Configuration
public class AdapterConfiguration {

    @Bean
    AccountAdapter accountAdapter(AccountRepository accountRepository) {
        return new AccountAdapter(accountRepository);
    }

    @Bean
    BillAdapter billAdapter(BillRepository billRepository) {
        return new BillAdapter(billRepository);
    }

    @Bean
    HistoryAdapter historyAdapter(HistoryRepository historyRepository) {
        return new HistoryAdapter(historyRepository);
    }
}
