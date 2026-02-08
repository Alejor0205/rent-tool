package com.dardan.rent_tool.application.port.in;

import com.dardan.rent_tool.application.command.ChangeToolStatusCommand;
import com.dardan.rent_tool.application.command.CreateToolCommand;
import com.dardan.rent_tool.application.command.UpdateToolCommand;
import com.dardan.rent_tool.application.dto.ToolDTO;

import java.util.List;
import java.util.UUID;

public interface ToolUseCase {
    ToolDTO create(CreateToolCommand command);

    ToolDTO update(UpdateToolCommand command);

    ToolDTO get(UUID id);

    List<ToolDTO> list();

    List<ToolDTO> listAvailable();

    ToolDTO changeStatus(ChangeToolStatusCommand command);

    void delete(UUID id);
}
