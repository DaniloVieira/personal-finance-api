package br.com.personalfinace.personalfinanceapi.business.transaction.dto;

import br.com.personalfinace.personalfinanceapi.business.tag.dto.TagResponse;
import br.com.personalfinace.personalfinanceapi.business.transaction.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public record TransactionResponse (
    Long id,
    BigDecimal amount,
    TransactionType type,
    String description,
    LocalDate date,
    Long accountId,
    String accountName,
    Long parentTransactionId,
    Long transferGroupId,
    Set<TagResponse>tags
) {}
