package com.dardan.rent_tool.application.usecase.tool;

import com.dardan.rent_tool.application.exception.NotFoundException;
import com.dardan.rent_tool.application.port.out.ToolOutputPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteToolUseCase {

    private final ToolOutputPort toolOutputPort;

    public DeleteToolUseCase(ToolOutputPort toolOutputPort) {
        this.toolOutputPort = toolOutputPort;
    }

    public void execute(UUID id) {
        if (!toolOutputPort.existsById(id)) {
            throw new NotFoundException("toolId", "La herramienta no existe.");
        }
        toolOutputPort.deleteById(id);
    }
}
