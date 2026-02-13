package br.com.personalfinace.personalfinanceapi.business.transaction.customs;

import br.com.personalfinace.personalfinanceapi.business.transaction.Transaction;

import java.util.List;

public interface TransactionRepositoryCustom {

    List<Transaction> findAllByTagIncludingSubtags(Long tagId);
}
