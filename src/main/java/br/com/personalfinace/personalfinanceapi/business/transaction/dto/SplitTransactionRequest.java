package br.com.personalfinace.personalfinanceapi.business.transaction.dto;

import java.math.BigDecimal;

public record SplitTransactionRequest (

        BigDecimal amount

) {
}
