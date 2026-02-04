package com.dardan.rent_tool.application.usecase.tool;

import com.dardan.rent_tool.application.dto.ToolDTO;
import com.dardan.rent_tool.application.mapper.ToolDTOMapper;
import com.dardan.rent_tool.application.port.out.ToolOutputPort;
import com.dardan.rent_tool.domain.model.enumm.ToolStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAvailableToolsUseCase {

    private final ToolOutputPort toolOutputPort;
    private final ToolDTOMapper mapper = new ToolDTOMapper();

    public ListAvailableToolsUseCase(ToolOutputPort toolOutputPort) {
        this.toolOutputPort = toolOutputPort;
    }

    public List<ToolDTO> execute() {
        return toolOutputPort.findByStatus(ToolStatus.AVAILABLE).stream()
            .map(mapper::toDTO)
            .toList();
    }
}
