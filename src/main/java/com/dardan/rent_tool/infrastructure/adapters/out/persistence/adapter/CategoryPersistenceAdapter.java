package com.dardan.rent_tool.infrastructure.adapters.out.persistence.adapter;

import com.dardan.rent_tool.application.port.out.CategoryOutputPort;
import com.dardan.rent_tool.domain.model.entity.Category;
import com.dardan.rent_tool.infrastructure.adapters.out.persistence.mappers.CategoryMapper;
import com.dardan.rent_tool.infrastructure.adapters.out.persistence.repository.JpaCategoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class CategoryPersistenceAdapter implements CategoryOutputPort {

    private final JpaCategoryRepository categoryRepository;
    private final CategoryMapper mapper = new CategoryMapper();

    public CategoryPersistenceAdapter(JpaCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(Category category) {
        return mapper.toDomain(categoryRepository.save(mapper.toEntity(category)));
    }

    @Override
    public Optional<Category> findById(UUID id) {
        return categoryRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void deleteById(UUID id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public boolean existsByNameIgnoreCase(String name) {
        return categoryRepository.existsByNameIgnoreCase(name);
    }
}
