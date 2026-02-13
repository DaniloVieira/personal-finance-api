package br.com.personalfinace.personalfinanceapi.business.transaction.dto;

import br.com.personalfinace.personalfinanceapi.business.tag.dto.TagResponse;
import br.com.personalfinace.personalfinanceapi.business.transaction.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record TransactionRequest (

        Long id,
        TransactionType transactionType,
        String description,
        LocalDate date,
        Long accountId,
        String accountName,
        Long parentTransactionId,
        Long transferGroupId,
        BigDecimal amount,
        Set<TagResponse> tags

) {

    public List<Long> getTagIds() {
        return tags.stream().map(TagResponse::id).toList();
    }

}
