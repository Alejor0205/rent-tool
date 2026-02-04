package com.dardan.rent_tool.infrastructure.adapters.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "invoices")
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private UUID rentalId;

    @Lob
    @Column(nullable = false, columnDefinition = "LONGBLOB")
    private byte[] pdfData;

    @Column(nullable = false)
    private OffsetDateTime createdAt;

    public InvoiceEntity() {
    }

    public InvoiceEntity(UUID id, UUID rentalId, byte[] pdfData, OffsetDateTime createdAt) {
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
