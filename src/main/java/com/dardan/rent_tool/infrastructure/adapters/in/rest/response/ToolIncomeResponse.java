package com.dardan.rent_tool.infrastructure.adapters.in.rest.response;

import java.math.BigDecimal;
import java.util.UUID;

public class ToolIncomeResponse {
    private UUID toolId;
    private BigDecimal totalIncome;

    public ToolIncomeResponse() {
    }

    public ToolIncomeResponse(UUID toolId, BigDecimal totalIncome) {
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
