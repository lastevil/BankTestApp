package ru.aston.banktest.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.aston.banktest.contract.account.output.AccountOutputManager;
import ru.aston.banktest.contract.bill.input.BillCreateUseCase;
import ru.aston.banktest.contract.bill.output.BillOutputManager;
import ru.aston.banktest.contract.history.input.SaveOperationUseCase;
import ru.aston.banktest.contract.history.output.HistoryOutputManager;
import ru.aston.banktest.core.service.AccountService;
import ru.aston.banktest.core.service.BillService;
import ru.aston.banktest.core.service.HistoryService;

@Configuration
public class BeanConfiguration {
    @Bean
    AccountService accountService(AccountOutputManager accountOutputManager, BillCreateUseCase billCreateUseCase) {
        return new AccountService(accountOutputManager, billCreateUseCase);
    }

    @Bean
    BillService billService(BillOutputManager billOutputManager, SaveOperationUseCase saveOperationUseCase){
        return new BillService(billOutputManager, saveOperationUseCase);
    }

    @Bean
    HistoryService historyService(HistoryOutputManager historyOutputManager){
        return new HistoryService(historyOutputManager);
    }
}
