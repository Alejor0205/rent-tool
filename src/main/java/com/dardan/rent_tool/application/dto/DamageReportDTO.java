package com.dardan.rent_tool.application.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public class DamageReportDTO {
    private UUID id;
    private UUID rentalId;
    private UUID toolId;
    private String description;
    private boolean resolved;
    private OffsetDateTime createdAt;

    public DamageReportDTO() {
    }

    public DamageReportDTO(UUID id,
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
