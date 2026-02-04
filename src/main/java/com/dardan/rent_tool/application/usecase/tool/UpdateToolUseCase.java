package com.dardan.rent_tool.application.usecase.tool;

import com.dardan.rent_tool.application.command.UpdateToolCommand;
import com.dardan.rent_tool.application.dto.ToolDTO;
import com.dardan.rent_tool.application.exception.NotFoundException;
import com.dardan.rent_tool.application.mapper.ToolDTOMapper;
import com.dardan.rent_tool.application.port.out.CategoryOutputPort;
import com.dardan.rent_tool.application.port.out.ToolOutputPort;
import com.dardan.rent_tool.domain.model.entity.Category;
import com.dardan.rent_tool.domain.model.entity.RentTool;
import com.dardan.rent_tool.domain.validation.ToolValidator;
import org.springframework.stereotype.Service;

@Service
public class UpdateToolUseCase {

    private final ToolOutputPort toolOutputPort;
    private final CategoryOutputPort categoryOutputPort;
    private final ToolValidator validator = new ToolValidator();
    private final ToolDTOMapper mapper = new ToolDTOMapper();

    public UpdateToolUseCase(ToolOutputPort toolOutputPort, CategoryOutputPort categoryOutputPort) {
        this.toolOutputPort = toolOutputPort;
        this.categoryOutputPort = categoryOutputPort;
    }

    public ToolDTO execute(UpdateToolCommand command) {
        RentTool existing = toolOutputPort.findById(command.getId())
            .orElseThrow(() -> new NotFoundException("toolId", "La herramienta no existe."));

        if (command.getName() != null) {
            validator.validateName(command.getName());
            existing.setName(command.getName().trim());
        }

        if (command.getCategoryId() != null) {
            Category category = categoryOutputPort.findById(command.getCategoryId())
                .orElseThrow(() -> new NotFoundException("categoryId", "La categoría no existe."));
            existing.setCategory(category);
        }

        if (command.getHourlyRate() != null || command.getDailyRate() != null) {
            validator.validateRates(command.getHourlyRate(), command.getDailyRate());
            if (command.getHourlyRate() != null) {
                existing.setHourlyRate(command.getHourlyRate());
            }
            if (command.getDailyRate() != null) {
                existing.setDailyRate(command.getDailyRate());
            }
        }

        if (command.getStatus() != null) {
            existing.setStatus(command.getStatus());
        }

        return mapper.toDTO(toolOutputPort.save(existing));
    }
}
