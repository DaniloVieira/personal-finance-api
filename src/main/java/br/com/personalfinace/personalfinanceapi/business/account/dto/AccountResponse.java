package br.com.personalfinace.personalfinanceapi.business.account.dto;

import java.math.BigDecimal;

public record AccountResponse(

        Long id,
        String name,
        BigDecimal initialFunds

) {
}
