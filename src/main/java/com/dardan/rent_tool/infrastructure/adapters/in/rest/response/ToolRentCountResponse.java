package com.dardan.rent_tool.infrastructure.adapters.in.rest.response;

import java.util.UUID;

public class ToolRentCountResponse {
    private UUID toolId;
    private long total;

    public ToolRentCountResponse() {
    }

    public ToolRentCountResponse(UUID toolId, long total) {
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
