package com.dardan.rent_tool.application.usecase.admin;

import com.dardan.rent_tool.application.dto.DamageReportDTO;
import com.dardan.rent_tool.application.mapper.DamageReportDTOMapper;
import com.dardan.rent_tool.application.port.out.DamageReportOutputPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListDamageReportsUseCase {

    private final DamageReportOutputPort damageReportOutputPort;
    private final DamageReportDTOMapper mapper = new DamageReportDTOMapper();

    public ListDamageReportsUseCase(DamageReportOutputPort damageReportOutputPort) {
        this.damageReportOutputPort = damageReportOutputPort;
    }

    public List<DamageReportDTO> execute() {
        return damageReportOutputPort.findAll().stream().map(mapper::toDTO).toList();
    }
}
