package com.dardan.rent_tool.infrastructure.adapters.in.rest.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UpdateRentalRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal totalAmount;

    public UpdateRentalRequest() {
    }

    public UpdateRentalRequest(LocalDate startDate, LocalDate endDate, BigDecimal totalAmount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalAmount = totalAmount;
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
