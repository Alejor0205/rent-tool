package com.dardan.rent_tool.infrastructure.adapters.in.rest.response;

import com.dardan.rent_tool.domain.model.enumm.ToolStatus;

import java.math.BigDecimal;
import java.util.UUID;

public class ToolResponse {
    private UUID id;
    private String name;
    private UUID categoryId;
    private String categoryName;
    private BigDecimal hourlyRate;
    private BigDecimal dailyRate;
    private ToolStatus status;
    private String description;
    private String imagePath;

    public ToolResponse() {
    }

    public ToolResponse(UUID id,
                        String name,
                        UUID categoryId,
                        String categoryName,
                        BigDecimal hourlyRate,
                        BigDecimal dailyRate,
                        ToolStatus status,
                        String description,
                        String imagePath) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.hourlyRate = hourlyRate;
        this.dailyRate = dailyRate;
        this.status = status;
        this.description = description;
        this.imagePath = imagePath;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public BigDecimal getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(BigDecimal dailyRate) {
        this.dailyRate = dailyRate;
    }

    public ToolStatus getStatus() {
        return status;
    }

    public void setStatus(ToolStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
