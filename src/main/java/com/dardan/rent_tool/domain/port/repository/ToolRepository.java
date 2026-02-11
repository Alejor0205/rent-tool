package com.dardan.rent_tool.domain.port.repository;

import com.dardan.rent_tool.domain.model.entity.Tool;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ToolRepository extends JpaRepository<Tool, String> {
}
