package br.com.personalfinace.personalfinanceapi.business.account;

import br.com.personalfinace.personalfinanceapi.business.account.dto.AccountRequest;
import br.com.personalfinace.personalfinanceapi.business.account.dto.AccountResponse;
import br.com.personalfinace.personalfinanceapi.common.dto.Response;
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
    public ResponseEntity<AccountResponse> save(@RequestBody AccountRequest accountRequest) {
        return ResponseEntity.ok(accountService.save(accountRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.delete(id));
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> findAll() {
        return ResponseEntity.ok(accountService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.findById(id));
    }

}
