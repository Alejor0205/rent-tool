package com.dardan.rent_tool.infrastructure.adapters.in.rest.request;

import com.dardan.rent_tool.domain.model.enumm.ToolStatus;

import java.math.BigDecimal;
import java.util.UUID;

public class UpdateToolRequest {
    private String name;
    private UUID categoryId;
    private BigDecimal hourlyRate;
    private BigDecimal dailyRate;
    private ToolStatus status;

    public UpdateToolRequest() {
    }

    public UpdateToolRequest(String name,
                             UUID categoryId,
                             BigDecimal hourlyRate,
                             BigDecimal dailyRate,
                             ToolStatus status) {
        this.name = name;
        this.categoryId = categoryId;
        this.hourlyRate = hourlyRate;
        this.dailyRate = dailyRate;
        this.status = status;
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
}
