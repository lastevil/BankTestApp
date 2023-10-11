package ru.aston.banktest.core.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.aston.banktest.contract.bill.input.BillCashOperationsUseCase;
import ru.aston.banktest.contract.bill.input.BillVirtualOperationsUseCase;
import ru.aston.banktest.contract.history.input.GetHistoryUseCase;
import ru.aston.banktest.dto.input.bill.BillDepositeOperationInputDto;
import ru.aston.banktest.dto.input.bill.BillTransferOperationInputDto;
import ru.aston.banktest.dto.input.bill.BillWithdrawOperationInputDto;
import ru.aston.banktest.dto.input.validation.PinValidator;
import ru.aston.banktest.dto.output.HistoryOutputDto;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/operation")
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
    public void deposite(@RequestBody BillDepositeOperationInputDto depositeDto) {
        billCashOperationsUseCase.deposite(depositeDto.billNumber(), depositeDto.sum());
    }

    @PostMapping("/withdraw")
    public void withdraw(@RequestBody BillWithdrawOperationInputDto billDto) {
        PinValidator.isValid(billDto.pinCode());
        billCashOperationsUseCase.withdraw(billDto.billNumber(), billDto.sum(), billDto.pinCode());
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody BillTransferOperationInputDto transferDto) {
        PinValidator.isValid(transferDto.pinCode());
        billVirtualOperationsUseCase.transfer(transferDto.fromBill(), transferDto.toBill(), transferDto.sum(), transferDto.pinCode());
    }

    @GetMapping("/history")
    public Page<HistoryOutputDto> getBillHistory(@RequestParam(name = "bill") String bill,
                                                 @RequestParam(name = "page", defaultValue = "1") Integer page,
                                                 @RequestParam(name = "elements_count", defaultValue = "10") Integer elementsCount,
                                                 @RequestParam(name = "before", required = false) LocalDateTime before,
                                                 @RequestParam(name = "after", required = false) LocalDateTime after) {

        return getHistoryUseCase.getBillHistory(bill,page,elementsCount, before,after);
    }
}
