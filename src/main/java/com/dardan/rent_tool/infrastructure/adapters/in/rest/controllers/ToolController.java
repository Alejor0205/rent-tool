package com.dardan.rent_tool.infrastructure.adapters.in.rest.controllers;

import com.dardan.rent_tool.application.command.ChangeToolStatusCommand;
import com.dardan.rent_tool.application.command.CreateToolCommand;
import com.dardan.rent_tool.application.command.UpdateToolCommand;
import com.dardan.rent_tool.application.dto.ToolDTO;
import com.dardan.rent_tool.application.usecase.tool.ChangeToolStatusUseCase;
import com.dardan.rent_tool.application.usecase.tool.CreateToolUseCase;
import com.dardan.rent_tool.application.usecase.tool.DeleteToolUseCase;
import com.dardan.rent_tool.application.usecase.tool.GetToolUseCase;
import com.dardan.rent_tool.application.usecase.tool.ListAvailableToolsUseCase;
import com.dardan.rent_tool.application.usecase.tool.ListToolsUseCase;
import com.dardan.rent_tool.application.usecase.tool.UpdateToolUseCase;
import com.dardan.rent_tool.infrastructure.adapters.in.rest.request.ChangeToolStatusRequest;
import com.dardan.rent_tool.infrastructure.adapters.in.rest.request.CreateToolRequest;
import com.dardan.rent_tool.infrastructure.adapters.in.rest.request.UpdateToolRequest;
import com.dardan.rent_tool.infrastructure.adapters.in.rest.response.ToolResponse;
import com.dardan.rent_tool.infrastructure.storage.ToolImageStorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tools")
public class ToolController {

    private final CreateToolUseCase createToolUseCase;
    private final ListToolsUseCase listToolsUseCase;
    private final GetToolUseCase getToolUseCase;
    private final UpdateToolUseCase updateToolUseCase;
    private final DeleteToolUseCase deleteToolUseCase;
    private final ChangeToolStatusUseCase changeToolStatusUseCase;
    private final ListAvailableToolsUseCase listAvailableToolsUseCase;
    private final ToolImageStorageService toolImageStorageService;

    public ToolController(CreateToolUseCase createToolUseCase,
                          ListToolsUseCase listToolsUseCase,
                          GetToolUseCase getToolUseCase,
                          UpdateToolUseCase updateToolUseCase,
                          DeleteToolUseCase deleteToolUseCase,
                          ChangeToolStatusUseCase changeToolStatusUseCase,
                          ListAvailableToolsUseCase listAvailableToolsUseCase,
                          ToolImageStorageService toolImageStorageService) {
        this.createToolUseCase = createToolUseCase;
        this.listToolsUseCase = listToolsUseCase;
        this.getToolUseCase = getToolUseCase;
        this.updateToolUseCase = updateToolUseCase;
        this.deleteToolUseCase = deleteToolUseCase;
        this.changeToolStatusUseCase = changeToolStatusUseCase;
        this.listAvailableToolsUseCase = listAvailableToolsUseCase;
        this.toolImageStorageService = toolImageStorageService;
    }

    @PostMapping
    public ResponseEntity<ToolResponse> create(@RequestBody CreateToolRequest request) {
        ToolDTO dto = createToolUseCase.execute(
            new CreateToolCommand(
                request.getName(),
                request.getCategoryId(),
                request.getHourlyRate(),
                request.getDailyRate(),
                request.getDescription()
            )
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(dto));
    }

    @GetMapping
    public List<ToolResponse> list() {
        return listToolsUseCase.execute().stream().map(this::toResponse).toList();
    }

    @GetMapping("/available")
    public List<ToolResponse> listAvailable() {
        return listAvailableToolsUseCase.execute().stream().map(this::toResponse).toList();
    }

    @GetMapping("/{id}")
    public ToolResponse get(@PathVariable UUID id) {
        return toResponse(getToolUseCase.execute(id));
    }

    @PutMapping("/{id}")
    public ToolResponse update(@PathVariable UUID id, @RequestBody UpdateToolRequest request) {
        ToolDTO dto = updateToolUseCase.execute(
            new UpdateToolCommand(
                id,
                request.getName(),
                request.getCategoryId(),
                request.getHourlyRate(),
                request.getDailyRate(),
                request.getStatus(),
                request.getDescription(),
                null
            )
        );
        return toResponse(dto);
    }

    @PatchMapping("/{id}/status")
    public ToolResponse changeStatus(@PathVariable UUID id, @RequestBody ChangeToolStatusRequest request) {
        ToolDTO dto = changeToolStatusUseCase.execute(new ChangeToolStatusCommand(id, request.getStatus()));
        return toResponse(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteToolUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ToolResponse uploadImage(@PathVariable UUID id, @RequestParam("file") MultipartFile file) {
        String path = toolImageStorageService.store(id, file);
        ToolDTO updated = updateToolUseCase.execute(
            new UpdateToolCommand(
                id,
                null,
                null,
                null,
                null,
                null,
                null,
                path
            )
        );
        return toResponse(updated);
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<Resource> downloadImage(@PathVariable UUID id) {
        ToolDTO tool = getToolUseCase.execute(id);
        Path path = toolImageStorageService.load(tool.getImagePath());
        if (path == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            Resource resource = new UrlResource(path.toUri());
            MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;
            try {
                String detected = java.nio.file.Files.probeContentType(path);
                if (detected != null) {
                    mediaType = MediaType.parseMediaType(detected);
                }
            } catch (java.io.IOException ignored) {
            }
            return ResponseEntity.ok()
                .contentType(mediaType)
                .body(resource);
        } catch (MalformedURLException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    private ToolResponse toResponse(ToolDTO dto) {
        return new ToolResponse(
            dto.getId(),
            dto.getName(),
            dto.getCategoryId(),
            dto.getCategoryName(),
            dto.getHourlyRate(),
            dto.getDailyRate(),
            dto.getStatus(),
            dto.getDescription(),
            dto.getImagePath()
        );
    }
}
