package com.dardan.rent_tool.application.usecase.admin;

import com.dardan.rent_tool.application.dto.DamageReportDTO;
import com.dardan.rent_tool.application.exception.NotFoundException;
import com.dardan.rent_tool.application.mapper.DamageReportDTOMapper;
import com.dardan.rent_tool.application.port.out.DamageReportOutputPort;
import com.dardan.rent_tool.domain.model.entity.DamageReport;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ResolveDamageReportUseCase {

    private final DamageReportOutputPort damageReportOutputPort;
    private final DamageReportDTOMapper mapper = new DamageReportDTOMapper();

    public ResolveDamageReportUseCase(DamageReportOutputPort damageReportOutputPort) {
        this.damageReportOutputPort = damageReportOutputPort;
    }

    public DamageReportDTO execute(UUID id) {
        DamageReport report = damageReportOutputPort.findById(id)
            .orElseThrow(() -> new NotFoundException("damageReportId", "El reporte de daño no existe."));
        report.setResolved(true);
        return mapper.toDTO(damageReportOutputPort.save(report));
    }
}
