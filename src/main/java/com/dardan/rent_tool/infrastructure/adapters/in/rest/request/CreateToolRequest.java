package com.dardan.rent_tool.infrastructure.adapters.in.rest.request;

import java.math.BigDecimal;
import java.util.UUID;

public class CreateToolRequest {
    private String name;
    private UUID categoryId;
    private BigDecimal hourlyRate;
    private BigDecimal dailyRate;

    public CreateToolRequest() {
    }

    public CreateToolRequest(String name, UUID categoryId, BigDecimal hourlyRate, BigDecimal dailyRate) {
        this.name = name;
        this.categoryId = categoryId;
        this.hourlyRate = hourlyRate;
        this.dailyRate = dailyRate;
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
}
