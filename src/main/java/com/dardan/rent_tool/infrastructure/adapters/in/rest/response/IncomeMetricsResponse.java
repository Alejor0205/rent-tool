package com.dardan.rent_tool.infrastructure.adapters.in.rest.response;

import java.math.BigDecimal;

public class IncomeMetricsResponse {
    private BigDecimal totalIncome;

    public IncomeMetricsResponse() {
    }

    public IncomeMetricsResponse(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }
}
