package br.com.personalfinace.personalfinanceapi.business.transaction.dto;

import java.math.BigDecimal;

public record DashboardSummary(
        BigDecimal totalBalance,
        BigDecimal totalIncome,
        BigDecimal totalExpenses,
        BigDecimal percentageChange
) {}
