package com.dardan.rent_tool.domain.port.repository;

import com.dardan.rent_tool.domain.model.entity.RentTool;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RentToolRepository {
    RentTool save(RentTool tool);

    Optional<RentTool> findById(UUID id);

    List<RentTool> findAll();

    void deleteById(UUID id);

    boolean existsById(UUID id);
}
