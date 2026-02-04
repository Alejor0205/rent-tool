package com.dardan.rent_tool.application.mapper;

import com.dardan.rent_tool.application.dto.ToolDTO;
import com.dardan.rent_tool.domain.model.entity.RentTool;

public class ToolDTOMapper {

    public ToolDTO toDTO(RentTool tool) {
        if (tool == null) {
            return null;
        }
        return new ToolDTO(
            tool.getId(),
            tool.getName(),
            tool.getCategory() != null ? tool.getCategory().getId() : null,
            tool.getCategory() != null ? tool.getCategory().getName() : null,
            tool.getHourlyRate(),
            tool.getDailyRate(),
            tool.getStatus()
        );
    }
}
