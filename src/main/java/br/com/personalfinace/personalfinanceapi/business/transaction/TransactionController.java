package br.com.personalfinace.personalfinanceapi.business.transaction;

import br.com.personalfinace.personalfinanceapi.business.transaction.dto.TransactionRequest;
import br.com.personalfinace.personalfinanceapi.business.transaction.dto.TransactionResponse;
import br.com.personalfinace.personalfinanceapi.common.dto.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponse> save(@Valid @RequestBody TransactionRequest request) {
        return ResponseEntity.ok(transactionService.save(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.delete(id));
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> findAll() {
        return ResponseEntity.ok(transactionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.findById(id));
    }

}
