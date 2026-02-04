package com.dardan.rent_tool.infrastructure.adapters.out.persistence.adapter;

import com.dardan.rent_tool.application.port.out.ToolOutputPort;
import com.dardan.rent_tool.domain.model.entity.RentTool;
import com.dardan.rent_tool.infrastructure.adapters.out.persistence.entity.CategoryEntity;
import com.dardan.rent_tool.infrastructure.adapters.out.persistence.entity.ToolEntity;
import com.dardan.rent_tool.infrastructure.adapters.out.persistence.mappers.ToolMapper;
import com.dardan.rent_tool.infrastructure.adapters.out.persistence.repository.JpaCategoryRepository;
import com.dardan.rent_tool.infrastructure.adapters.out.persistence.repository.JpaToolRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ToolPersistenceAdapter implements ToolOutputPort {

    private final JpaToolRepository toolRepository;
    private final JpaCategoryRepository categoryRepository;
    private final ToolMapper mapper = new ToolMapper();

    public ToolPersistenceAdapter(JpaToolRepository toolRepository, JpaCategoryRepository categoryRepository) {
        this.toolRepository = toolRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public RentTool save(RentTool tool) {
        CategoryEntity categoryEntity = categoryRepository.findById(tool.getCategory().getId())
            .orElseThrow();
        ToolEntity entity = mapper.toEntity(tool, categoryEntity);
        ToolEntity saved = toolRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<RentTool> findById(UUID id) {
        return toolRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<RentTool> findAll() {
        return toolRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void deleteById(UUID id) {
        toolRepository.deleteById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return toolRepository.existsById(id);
    }

    @Override
    public List<RentTool> findByStatus(com.dardan.rent_tool.domain.model.enumm.ToolStatus status) {
        return toolRepository.findByStatus(status).stream()
            .map(mapper::toDomain)
            .toList();
    }
}
