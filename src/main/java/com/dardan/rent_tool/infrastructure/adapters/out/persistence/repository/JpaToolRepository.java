package com.dardan.rent_tool.infrastructure.adapters.out.persistence.repository;

import com.dardan.rent_tool.infrastructure.adapters.out.persistence.entity.ToolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaToolRepository extends JpaRepository<ToolEntity, UUID> {
}
