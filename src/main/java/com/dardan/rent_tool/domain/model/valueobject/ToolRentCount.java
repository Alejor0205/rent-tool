package com.dardan.rent_tool.domain.model.valueobject;

import java.util.UUID;

public class ToolRentCount {
    private final UUID toolId;
    private final long total;

    public ToolRentCount(UUID toolId, long total) {
        this.toolId = toolId;
        this.total = total;
    }

    public UUID getToolId() {
        return toolId;
    }

    public long getTotal() {
        return total;
    }
}
