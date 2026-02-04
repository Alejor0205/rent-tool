package com.dardan.rent_tool.domain.model.valueobject;

import java.math.BigDecimal;
import java.util.UUID;

public class ToolIncome {
    private final UUID toolId;
    private final BigDecimal totalIncome;

    public ToolIncome(UUID toolId, BigDecimal totalIncome) {
        this.toolId = toolId;
        this.totalIncome = totalIncome;
    }

    public UUID getToolId() {
        return toolId;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }
}
