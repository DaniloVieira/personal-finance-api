package br.com.personalfinace.personalfinanceapi.business.account;

import br.com.personalfinace.personalfinanceapi.business.account.dto.AccountRequest;
import br.com.personalfinace.personalfinanceapi.business.account.dto.AccountResponse;
import br.com.personalfinace.personalfinanceapi.common.dto.Response;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountService {

    private AccountRepository accountRepository;

    private AccountResponse toResponse(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getName(),
                account.getInitialFunds()
        );
    }

    private Account toEntity(AccountRequest accountRequest) {
        Account account = new Account();
        account.setName(accountRequest.name());
        account.setInitialFunds(accountRequest.initialFunds());
        return account;
    }

    public AccountResponse findById(Long id) {
        Account account = getAccount(id);
        if (account == null) {
            Exception e = new Exception(String.format("Account not found, id:%d", id));
            e.printStackTrace(); //TODO replace for logging
            return null;
        }
        return toResponse(account);
    }

    public @Nullable Account getAccount(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    public AccountResponse save(AccountRequest accountRequest) {
        Account account = toEntity(accountRequest);
        return toResponse(accountRepository.save(account));
    }

    public List<AccountResponse> findAll() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public Response delete(Long id) {
        Account account = getAccount(id);
        if (Objects.isNull(account)) {
            return null;
        }
        return new Response("Account deleted");
    }
}
