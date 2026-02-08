package com.dardan.rent_tool.application.usecase.tool;

import com.dardan.rent_tool.application.command.CreateToolCommand;
import com.dardan.rent_tool.application.dto.ToolDTO;
import com.dardan.rent_tool.application.exception.NotFoundException;
import com.dardan.rent_tool.application.mapper.ToolDTOMapper;
import com.dardan.rent_tool.application.port.out.CategoryOutputPort;
import com.dardan.rent_tool.application.port.out.ToolOutputPort;
import com.dardan.rent_tool.domain.model.entity.Category;
import com.dardan.rent_tool.domain.model.entity.RentTool;
import com.dardan.rent_tool.domain.model.enumm.ToolStatus;
import com.dardan.rent_tool.domain.validation.ToolValidator;
import org.springframework.stereotype.Service;

@Service
public class CreateToolUseCase {

    private final ToolOutputPort toolOutputPort;
    private final CategoryOutputPort categoryOutputPort;
    private final ToolValidator validator = new ToolValidator();
    private final ToolDTOMapper mapper = new ToolDTOMapper();

    public CreateToolUseCase(ToolOutputPort toolOutputPort, CategoryOutputPort categoryOutputPort) {
        this.toolOutputPort = toolOutputPort;
        this.categoryOutputPort = categoryOutputPort;
    }

    public ToolDTO execute(CreateToolCommand command) {
        validator.validateName(command.getName());
        validator.validateCategoryId(command.getCategoryId());
        validator.validateRates(command.getHourlyRate(), command.getDailyRate());

        Category category = categoryOutputPort.findById(command.getCategoryId())
            .orElseThrow(() -> new NotFoundException("categoryId", "La categoría no existe."));

        RentTool tool = new RentTool(
            null,
            command.getName().trim(),
            category,
            command.getHourlyRate(),
            command.getDailyRate(),
            ToolStatus.AVAILABLE,
            command.getDescription(),
            null
        );

        return mapper.toDTO(toolOutputPort.save(tool));
    }
}
