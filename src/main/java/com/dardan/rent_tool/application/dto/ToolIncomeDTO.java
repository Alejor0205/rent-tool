package com.dardan.rent_tool.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class ToolIncomeDTO {
    private UUID toolId;
    private BigDecimal totalIncome;

    public ToolIncomeDTO() {
    }

    public ToolIncomeDTO(UUID toolId, BigDecimal totalIncome) {
        this.toolId = toolId;
        this.totalIncome = totalIncome;
    }

    public UUID getToolId() {
        return toolId;
    }

    public void setToolId(UUID toolId) {
        this.toolId = toolId;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }
}
