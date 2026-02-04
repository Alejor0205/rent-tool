package com.dardan.rent_tool.application.command;

import java.util.UUID;

public class ConfirmReturnCommand {
    private UUID rentalId;
    private boolean damaged;
    private String damageDescription;

    public ConfirmReturnCommand() {
    }

    public ConfirmReturnCommand(UUID rentalId, boolean damaged, String damageDescription) {
        this.rentalId = rentalId;
        this.damaged = damaged;
        this.damageDescription = damageDescription;
    }

    public UUID getRentalId() {
        return rentalId;
    }

    public void setRentalId(UUID rentalId) {
        this.rentalId = rentalId;
    }

    public boolean isDamaged() {
        return damaged;
    }

    public void setDamaged(boolean damaged) {
        this.damaged = damaged;
    }

    public String getDamageDescription() {
        return damageDescription;
    }

    public void setDamageDescription(String damageDescription) {
        this.damageDescription = damageDescription;
    }
}
