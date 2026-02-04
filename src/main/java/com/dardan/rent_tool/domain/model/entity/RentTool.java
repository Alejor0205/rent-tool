package com.dardan.rent_tool.domain.model.entity;

import com.dardan.rent_tool.domain.model.enumm.ToolStatus;

import java.math.BigDecimal;
import java.util.UUID;

public class RentTool {
    private UUID id;
    private String name;
    private Category category;
    private BigDecimal hourlyRate;
    private BigDecimal dailyRate;
    private ToolStatus status;

    public RentTool() {
    }

    public RentTool(UUID id,
                    String name,
                    Category category,
                    BigDecimal hourlyRate,
                    BigDecimal dailyRate,
                    ToolStatus status) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.hourlyRate = hourlyRate;
        this.dailyRate = dailyRate;
        this.status = status;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
