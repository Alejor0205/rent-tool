package com.dardan.rent_tool.domain.model.entity;

import com.dardan.rent_tool.domain.model.enumm.PaymentStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class Payment {
    private UUID id;
    private UUID rentalId;
    private BigDecimal amount;
    private PaymentStatus status;
    private String method;
    private OffsetDateTime createdAt;

    public Payment() {
    }

    public Payment(UUID id,
                   UUID rentalId,
                   BigDecimal amount,
                   PaymentStatus status,
                   String method,
                   OffsetDateTime createdAt) {
        this.id = id;
        this.rentalId = rentalId;
        this.amount = amount;
        this.status = status;
        this.method = method;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getRentalId() {
        return rentalId;
    }

    public void setRentalId(UUID rentalId) {
        this.rentalId = rentalId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
