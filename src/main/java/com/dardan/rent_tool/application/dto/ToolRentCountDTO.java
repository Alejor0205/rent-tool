package com.dardan.rent_tool.application.dto;

import java.util.UUID;

public class ToolRentCountDTO {
    private UUID toolId;
    private long total;

    public ToolRentCountDTO() {
    }

    public ToolRentCountDTO(UUID toolId, long total) {
        this.toolId = toolId;
        this.total = total;
    }

    public UUID getToolId() {
        return toolId;
    }

    public void setToolId(UUID toolId) {
        this.toolId = toolId;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
