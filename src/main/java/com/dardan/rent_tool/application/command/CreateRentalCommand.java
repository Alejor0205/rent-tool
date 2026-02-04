package com.dardan.rent_tool.application.command;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class CreateRentalCommand {
    private UUID toolId;
    private UUID customerId;
    private UUID providerId;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal totalAmount;

    public CreateRentalCommand() {
    }

    public CreateRentalCommand(UUID toolId,
                               UUID customerId,
                               UUID providerId,
                               LocalDate startDate,
                               LocalDate endDate,
                               BigDecimal totalAmount) {
        this.toolId = toolId;
        this.customerId = customerId;
        this.providerId = providerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalAmount = totalAmount;
    }

    public UUID getToolId() {
        return toolId;
    }

    public void setToolId(UUID toolId) {
        this.toolId = toolId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public UUID getProviderId() {
        return providerId;
    }

    public void setProviderId(UUID providerId) {
        this.providerId = providerId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
