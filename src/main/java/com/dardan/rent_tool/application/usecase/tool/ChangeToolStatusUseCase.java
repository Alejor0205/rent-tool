package com.dardan.rent_tool.application.usecase.tool;

import com.dardan.rent_tool.application.command.ChangeToolStatusCommand;
import com.dardan.rent_tool.application.dto.ToolDTO;
import com.dardan.rent_tool.application.exception.NotFoundException;
import com.dardan.rent_tool.application.mapper.ToolDTOMapper;
import com.dardan.rent_tool.application.port.out.ToolOutputPort;
import com.dardan.rent_tool.domain.model.entity.RentTool;
import org.springframework.stereotype.Service;

@Service
public class ChangeToolStatusUseCase {

    private final ToolOutputPort toolOutputPort;
    private final ToolDTOMapper mapper = new ToolDTOMapper();

    public ChangeToolStatusUseCase(ToolOutputPort toolOutputPort) {
        this.toolOutputPort = toolOutputPort;
    }

    public ToolDTO execute(ChangeToolStatusCommand command) {
        RentTool tool = toolOutputPort.findById(command.getId())
            .orElseThrow(() -> new NotFoundException("toolId", "La herramienta no existe."));
        tool.setStatus(command.getStatus());
        return mapper.toDTO(toolOutputPort.save(tool));
    }
}
