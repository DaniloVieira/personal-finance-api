package br.com.personalfinace.personalfinanceapi.business.account;

import br.com.personalfinace.personalfinanceapi.business.account.customs.AccountRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long>, AccountRepositoryCustom {
}
