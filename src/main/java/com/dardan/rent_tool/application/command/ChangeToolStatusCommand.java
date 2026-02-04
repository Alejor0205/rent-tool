package com.dardan.rent_tool.application.command;

import com.dardan.rent_tool.domain.model.enumm.ToolStatus;

import java.util.UUID;

public class ChangeToolStatusCommand {
    private UUID id;
    private ToolStatus status;

    public ChangeToolStatusCommand() {
    }

    public ChangeToolStatusCommand(UUID id, ToolStatus status) {
        this.id = id;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ToolStatus getStatus() {
        return status;
    }

    public void setStatus(ToolStatus status) {
        this.status = status;
    }
}
