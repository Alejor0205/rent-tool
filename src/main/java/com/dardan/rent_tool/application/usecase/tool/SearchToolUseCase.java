package com.dardan.rent_tool.application.usecase.tool;

import java.util.List;


import com.dardan.rent_tool.application.dto.ToolDTO;

import com.dardan.rent_tool.application.mapper.ToolDTOMapper;
import com.dardan.rent_tool.application.port.out.ToolOutputPort;
import com.dardan.rent_tool.domain.model.enumm.ToolStatus;

public class SearchToolUseCase {

    private final ToolOutputPort toolOutputPort;
    private final ToolDTOMapper mapper = new ToolDTOMapper();

    public SearchToolUseCase (ToolOutputPort toolOutputPort) {
        this.toolOutputPort = toolOutputPort;
    }

    public List<ToolDTO> execute() {
        return toolOutputPort.findByName(String name){
            .filter(toolOutputPort -> tool.getName().toLowerCase().contains(name.toList)
            .collect(controllers.toList))
        }
    }
}