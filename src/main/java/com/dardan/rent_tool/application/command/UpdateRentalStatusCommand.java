package com.dardan.rent_tool.application.command;

import com.dardan.rent_tool.domain.model.enumm.RentalStatus;

import java.util.UUID;

public class UpdateRentalStatusCommand {
    private UUID rentalId;
    private RentalStatus status;

    public UpdateRentalStatusCommand() {
    }

    public UpdateRentalStatusCommand(UUID rentalId, RentalStatus status) {
        this.rentalId = rentalId;
        this.status = status;
    }

    public UUID getRentalId() {
        return rentalId;
    }

    public void setRentalId(UUID rentalId) {
        this.rentalId = rentalId;
    }

    public RentalStatus getStatus() {
        return status;
    }

    public void setStatus(RentalStatus status) {
        this.status = status;
    }
}
