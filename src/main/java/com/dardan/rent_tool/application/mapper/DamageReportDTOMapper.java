package com.dardan.rent_tool.application.mapper;

import com.dardan.rent_tool.application.dto.DamageReportDTO;
import com.dardan.rent_tool.domain.model.entity.DamageReport;

public class DamageReportDTOMapper {
    public DamageReportDTO toDTO(DamageReport report) {
        if (report == null) {
            return null;
        }
        return new DamageReportDTO(
            report.getId(),
            report.getRentalId(),
            report.getToolId(),
            report.getDescription(),
            report.isResolved(),
            report.getCreatedAt()
        );
    }
}
