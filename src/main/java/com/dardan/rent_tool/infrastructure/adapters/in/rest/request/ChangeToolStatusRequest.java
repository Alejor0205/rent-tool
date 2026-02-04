package com.dardan.rent_tool.infrastructure.adapters.in.rest.request;

import com.dardan.rent_tool.domain.model.enumm.ToolStatus;

public class ChangeToolStatusRequest {
    private ToolStatus status;

    public ChangeToolStatusRequest() {
    }

    public ChangeToolStatusRequest(ToolStatus status) {
        this.status = status;
    }

    public ToolStatus getStatus() {
        return status;
    }

    public void setStatus(ToolStatus status) {
        this.status = status;
    }
}
