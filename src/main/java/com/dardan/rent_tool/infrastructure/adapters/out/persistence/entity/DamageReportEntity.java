package com.dardan.rent_tool.infrastructure.adapters.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "damage_reports")
public class DamageReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID rentalId;

    @Column(nullable = false)
    private UUID toolId;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false)
    private boolean resolved;

    @Column(nullable = false)
    private OffsetDateTime createdAt;

    public DamageReportEntity() {
    }

    public DamageReportEntity(UUID id,
                              UUID rentalId,
                              UUID toolId,
                              String description,
                              boolean resolved,
                              OffsetDateTime createdAt) {
        this.id = id;
        this.rentalId = rentalId;
        this.toolId = toolId;
        this.description = description;
        this.resolved = resolved;
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

    public UUID getToolId() {
        return toolId;
    }

    public void setToolId(UUID toolId) {
        this.toolId = toolId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
