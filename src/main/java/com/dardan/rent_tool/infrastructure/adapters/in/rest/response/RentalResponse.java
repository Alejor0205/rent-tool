package com.dardan.rent_tool.infrastructure.adapters.in.rest.response;

import com.dardan.rent_tool.domain.model.enumm.RentalStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

public class RentalResponse {
    private UUID id;
    private UUID toolId;
    private UUID customerId;
    private UUID providerId;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal totalAmount;
    private RentalStatus status;
    private OffsetDateTime createdAt;

    public RentalResponse() {
    }

    public RentalResponse(UUID id,
                          UUID toolId,
                          UUID customerId,
                          UUID providerId,
                          LocalDate startDate,
                          LocalDate endDate,
                          BigDecimal totalAmount,
                          RentalStatus status,
                          OffsetDateTime createdAt) {
        this.id = id;
        this.toolId = toolId;
        this.customerId = customerId;
        this.providerId = providerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public RentalStatus getStatus() {
        return status;
    }

    public void setStatus(RentalStatus status) {
        this.status = status;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
