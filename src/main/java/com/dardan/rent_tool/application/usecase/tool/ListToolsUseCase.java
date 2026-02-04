package com.dardan.rent_tool.application.usecase.tool;

import com.dardan.rent_tool.application.dto.ToolDTO;
import com.dardan.rent_tool.application.mapper.ToolDTOMapper;
import com.dardan.rent_tool.application.port.out.ToolOutputPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListToolsUseCase {

    private final ToolOutputPort toolOutputPort;
    private final ToolDTOMapper mapper = new ToolDTOMapper();

    public ListToolsUseCase(ToolOutputPort toolOutputPort) {
        this.toolOutputPort = toolOutputPort;
    }

    public List<ToolDTO> execute() {
        return toolOutputPort.findAll().stream().map(mapper::toDTO).toList();
    }
}
