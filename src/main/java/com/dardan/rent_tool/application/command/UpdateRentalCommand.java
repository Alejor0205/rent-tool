package com.dardan.rent_tool.application.command;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class UpdateRentalCommand {
    private UUID rentalId;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal totalAmount;

    public UpdateRentalCommand() {
    }

    public UpdateRentalCommand(UUID rentalId, LocalDate startDate, LocalDate endDate, BigDecimal totalAmount) {
        this.rentalId = rentalId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalAmount = totalAmount;
    }

    public UUID getRentalId() {
        return rentalId;
    }

    public void setRentalId(UUID rentalId) {
        this.rentalId = rentalId;
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
