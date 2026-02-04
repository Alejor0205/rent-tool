package com.dardan.rent_tool.domain.port.repository;

import com.dardan.rent_tool.domain.model.entity.DamageReport;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DamageReportRepository {
    DamageReport save(DamageReport report);

    Optional<DamageReport> findById(UUID id);

    List<DamageReport> findAll();
}
