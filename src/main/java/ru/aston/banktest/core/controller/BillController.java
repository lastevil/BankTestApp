package ru.aston.banktest.core.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.aston.banktest.contract.bill.input.BillCashOperationsUseCase;
import ru.aston.banktest.contract.bill.input.BillVirtualOperationsUseCase;
import ru.aston.banktest.contract.history.input.GetHistoryUseCase;
import ru.aston.banktest.dto.input.bill.BillDepositeOperationInputDto;
import ru.aston.banktest.dto.input.bill.BillTransferOperationInputDto;
import ru.aston.banktest.dto.input.bill.BillWithdrawOperationInputDto;
import ru.aston.banktest.dto.input.validation.AppValidator;
import ru.aston.banktest.dto.output.BillOutputDto;
import ru.aston.banktest.dto.output.HistoryOutputDto;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/api/v1/operation")
@Tag(name = "Bill", description = "Контроллер произведения операций над счетом")
public class BillController {
    private final BillCashOperationsUseCase billCashOperationsUseCase;
    private final BillVirtualOperationsUseCase billVirtualOperationsUseCase;

    private final GetHistoryUseCase getHistoryUseCase;

    public BillController(BillCashOperationsUseCase billCashOperationsUseCase, BillVirtualOperationsUseCase billVirtualOperationsUseCase, GetHistoryUseCase getHistoryUseCase) {
        this.billCashOperationsUseCase = billCashOperationsUseCase;
        this.billVirtualOperationsUseCase = billVirtualOperationsUseCase;
        this.getHistoryUseCase = getHistoryUseCase;
    }

    @PostMapping("/deposite")
    @Operation(summary = "Пополнение счета", description = "Пополнение счета")
    public BillOutputDto deposite(@RequestBody BillDepositeOperationInputDto depositeDto) {
        AppValidator.validateMoney(depositeDto.sum());
        return billCashOperationsUseCase.deposite(depositeDto.billNumber(), depositeDto.sum());
    }

    @PostMapping("withdraw")
    @Operation(summary = "Снятие средств с счета", description = "Снятие средств с счета")
    public BillOutputDto withdraw(@RequestBody BillWithdrawOperationInputDto withdrawDto) {
        AppValidator.validatePin(withdrawDto.pinCode());
        AppValidator.validateMoney(withdrawDto.sum());
        return billCashOperationsUseCase.withdraw(withdrawDto.billNumber(), withdrawDto.sum(), withdrawDto.pinCode());
    }

    @PostMapping("/transfer")
    @Operation(summary = "Перевод средств на с счета на счет", description = "Перевод средств на с счета на счет")
    public void transfer(@RequestBody BillTransferOperationInputDto transferDto) {
        AppValidator.validateBill(transferDto.fromBill(), transferDto.toBill());
        AppValidator.validateMoney(transferDto.sum());
        AppValidator.validatePin(transferDto.pinCode());
        billVirtualOperationsUseCase.transfer(transferDto.fromBill(), transferDto.toBill(), transferDto.sum(), transferDto.pinCode());
    }

    @GetMapping("/history")
    @Operation(summary = "Запрос операций по счету", description = "Запрос операций по счет")
    public Page<HistoryOutputDto> getBillHistory(@RequestParam(name = "bill") String bill,
                                                 @RequestParam(name = "page", defaultValue = "1") Integer page,
                                                 @RequestParam(name = "elements_count", defaultValue = "10") Integer elementsCount,
                                                 @RequestParam(name = "before", required = false) LocalDateTime before,
                                                 @RequestParam(name = "after", required = false) LocalDateTime after) {

        return getHistoryUseCase.getBillHistory(bill, page, elementsCount, before, after);
    }
}
