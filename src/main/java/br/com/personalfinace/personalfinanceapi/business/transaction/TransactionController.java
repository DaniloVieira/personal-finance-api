package br.com.personalfinace.personalfinanceapi.business.transaction;

import br.com.personalfinace.personalfinanceapi.business.transaction.dto.DashboardSummary;
import br.com.personalfinace.personalfinanceapi.business.transaction.dto.TransactionRequest;
import br.com.personalfinace.personalfinanceapi.business.transaction.dto.TransactionResponse;
import br.com.personalfinace.personalfinanceapi.common.dto.ApiResponse;
import br.com.personalfinace.personalfinanceapi.common.exception.BusinessException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<ApiResponse<TransactionResponse>> save(@Valid @RequestBody TransactionRequest request) {
        TransactionResponse response = transactionService.save(request);
        return ResponseEntity.ok(ApiResponse.created(response, "Transaction created successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        transactionService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Transaction deleted successfully"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TransactionResponse>>> findAll() {
        return ResponseEntity.ok(ApiResponse.success(transactionService.findAll(), "Transactions found"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TransactionResponse>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(transactionService.findById(id), "Transaction found"));
    }

    @PostMapping("/split/parent/{id}")
    public ResponseEntity<ApiResponse<List<TransactionResponse>>> splitTransaction(@PathVariable Long id, @Valid @RequestBody List<TransactionRequest> request) throws BusinessException {
        return ResponseEntity.ok(ApiResponse.success(transactionService.splitTransaction(id, request), "Transaction found"));
    }

    @GetMapping("/summary")
    public ResponseEntity<ApiResponse<DashboardSummary>> getDashboardSummary () {
        return ResponseEntity.ok(ApiResponse.success(transactionService.getDashboardSummary(), "Transactions summary retrieved"));
    }

}
