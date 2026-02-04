package com.dardan.rent_tool.infrastructure.adapters.out.persistence.mappers;

import com.dardan.rent_tool.domain.model.entity.DamageReport;
import com.dardan.rent_tool.infrastructure.adapters.out.persistence.entity.DamageReportEntity;

public class DamageReportMapper {
    public DamageReport toDomain(DamageReportEntity entity) {
        if (entity == null) {
            return null;
        }
        return new DamageReport(
            entity.getId(),
            entity.getRentalId(),
            entity.getToolId(),
            entity.getDescription(),
            entity.isResolved(),
            entity.getCreatedAt()
        );
    }

    public DamageReportEntity toEntity(DamageReport report) {
        if (report == null) {
            return null;
        }
        return new DamageReportEntity(
            report.getId(),
            report.getRentalId(),
            report.getToolId(),
            report.getDescription(),
            report.isResolved(),
            report.getCreatedAt()
        );
    }
}
