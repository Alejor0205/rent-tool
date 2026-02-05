package com.dardan.rent_tool.application.usecase.tool;

import com.dardan.rent_tool.application.command.ChangeToolStatusCommand;
import com.dardan.rent_tool.application.command.CreateToolCommand;
import com.dardan.rent_tool.application.command.UpdateToolCommand;
import com.dardan.rent_tool.application.dto.ToolDTO;
import com.dardan.rent_tool.application.port.in.ToolUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ToolUseCaseImpl implements ToolUseCase {

    private final CreateToolUseCase createToolUseCase;
    private final UpdateToolUseCase updateToolUseCase;
    private final GetToolUseCase getToolUseCase;
    private final ListToolsUseCase listToolsUseCase;
    private final ListAvailableToolsUseCase listAvailableToolsUseCase;
    private final ChangeToolStatusUseCase changeToolStatusUseCase;
    private final DeleteToolUseCase deleteToolUseCase;

    public ToolUseCaseImpl(CreateToolUseCase createToolUseCase,
                           UpdateToolUseCase updateToolUseCase,
                           GetToolUseCase getToolUseCase,
                           ListToolsUseCase listToolsUseCase,
                           ListAvailableToolsUseCase listAvailableToolsUseCase,
                           ChangeToolStatusUseCase changeToolStatusUseCase,
                           DeleteToolUseCase deleteToolUseCase) {
        this.createToolUseCase = createToolUseCase;
        this.updateToolUseCase = updateToolUseCase;
        this.getToolUseCase = getToolUseCase;
        this.listToolsUseCase = listToolsUseCase;
        this.listAvailableToolsUseCase = listAvailableToolsUseCase;
        this.changeToolStatusUseCase = changeToolStatusUseCase;
        this.deleteToolUseCase = deleteToolUseCase;
    }

    @Override
    public ToolDTO create(CreateToolCommand command) {
        return createToolUseCase.execute(command);
    }

    @Override
    public ToolDTO update(UpdateToolCommand command) {
        return updateToolUseCase.execute(command);
    }

    @Override
    public ToolDTO get(UUID id) {
        return getToolUseCase.execute(id);
    }

    @Override
    public List<ToolDTO> list() {
        return listToolsUseCase.execute();
    }

    @Override
    public List<ToolDTO> listAvailable() {
        return listAvailableToolsUseCase.execute();
    }

    @Override
    public ToolDTO changeStatus(ChangeToolStatusCommand command) {
        return changeToolStatusUseCase.execute(command);
    }

    @Override
    public void delete(UUID id) {
        deleteToolUseCase.execute(id);
    }
}
