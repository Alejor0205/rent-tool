package com.dardan.rent_tool.application.usecase.tool;


import com.dardan.rent_tool.domain.port.repository.ToolRepository;

import java.util.List;
import java.util.stream.Collectors;

public class SearchToolUseCase {

    private final ToolRepository toolRepository;

    public SearchToolUseCase(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    public List<com.dardan.rent_tool.domain.model.entity.Tool> searchToolsByName(String name) {
        return toolRepository.findAll().stream()
                .filter(tool -> tool.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
}
