package com.dardan.rent_tool.application.dto;

import java.math.BigDecimal;

public class IncomeMetricsDTO {
    private BigDecimal totalIncome;

    public IncomeMetricsDTO() {
    }

    public IncomeMetricsDTO(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }
}
