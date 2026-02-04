package com.dardan.rent_tool.application.usecase.tool;

import com.dardan.rent_tool.application.dto.ToolDTO;
import com.dardan.rent_tool.application.exception.NotFoundException;
import com.dardan.rent_tool.application.mapper.ToolDTOMapper;
import com.dardan.rent_tool.application.port.out.ToolOutputPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetToolUseCase {

    private final ToolOutputPort toolOutputPort;
    private final ToolDTOMapper mapper = new ToolDTOMapper();

    public GetToolUseCase(ToolOutputPort toolOutputPort) {
        this.toolOutputPort = toolOutputPort;
    }

    public ToolDTO execute(UUID id) {
        return toolOutputPort.findById(id)
            .map(mapper::toDTO)
            .orElseThrow(() -> new NotFoundException("toolId", "La herramienta no existe."));
    }
}
