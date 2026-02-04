package com.dardan.rent_tool.infrastructure.adapters.in.rest.request;

public class ConfirmReturnRequest {
    private boolean damaged;
    private String damageDescription;

    public ConfirmReturnRequest() {
    }

    public ConfirmReturnRequest(boolean damaged, String damageDescription) {
        this.damaged = damaged;
        this.damageDescription = damageDescription;
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
