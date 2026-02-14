package br.com.personalfinace.personalfinanceapi.business.account;

import br.com.personalfinace.personalfinanceapi.business.account.dto.AccountRequest;
import br.com.personalfinace.personalfinanceapi.business.account.dto.AccountResponse;
import br.com.personalfinace.personalfinanceapi.common.dto.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
@AllArgsConstructor
public class AccountController {

    private AccountService accountService;

    @PostMapping
    public ResponseEntity<ApiResponse<AccountResponse>> save(@RequestBody AccountRequest accountRequest) {
        return ResponseEntity.ok(ApiResponse.success(accountService.save(accountRequest), "Account saved"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        accountService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Account deleted"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AccountResponse>>> findAll() {
        return ResponseEntity.ok(ApiResponse.success(accountService.findAll(), "Accounts found"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AccountResponse>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(accountService.findById(id), "Account found"));
    }

}
