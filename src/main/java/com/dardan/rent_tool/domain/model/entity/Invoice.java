package com.dardan.rent_tool.domain.model.entity;

import java.time.OffsetDateTime;
import java.util.UUID;

public class Invoice {
    private UUID id;
    private UUID rentalId;
    private byte[] pdfData;
    private OffsetDateTime createdAt;

    public Invoice() {
    }

    public Invoice(UUID id, UUID rentalId, byte[] pdfData, OffsetDateTime createdAt) {
        this.id = id;
        this.rentalId = rentalId;
        this.pdfData = pdfData;
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

    public byte[] getPdfData() {
        return pdfData;
    }

    public void setPdfData(byte[] pdfData) {
        this.pdfData = pdfData;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
