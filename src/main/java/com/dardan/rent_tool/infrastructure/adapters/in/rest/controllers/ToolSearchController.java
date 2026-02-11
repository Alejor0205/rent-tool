package com.dardan.rent_tool.infrastructure.adapters.in.rest.controllers;

import com.dardan.rent_tool.application.usecase.tool.SearchToolUseCase;
import com.dardan.rent_tool.domain.model.entity.Tool;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ToolSearchController {

    private final SearchToolUseCase searchToolUseCase;

    public ToolSearchController(SearchToolUseCase searchToolUseCase) {
        this.searchToolUseCase = searchToolUseCase;
    }

    @GetMapping("/herramientas/search")
    public List<Tool> searchTools(@RequestParam String nombre) {
        return searchToolUseCase.searchToolsByName(nombre);
    }
}