package com.dardan.rent_tool.infrastructure.adapters.out.persistence.adapter;

import com.dardan.rent_tool.application.port.out.DamageReportOutputPort;
import com.dardan.rent_tool.domain.model.entity.DamageReport;
import com.dardan.rent_tool.infrastructure.adapters.out.persistence.mappers.DamageReportMapper;
import com.dardan.rent_tool.infrastructure.adapters.out.persistence.repository.JpaDamageReportRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class DamageReportPersistenceAdapter implements DamageReportOutputPort {

    private final JpaDamageReportRepository damageReportRepository;
    private final DamageReportMapper mapper = new DamageReportMapper();

    public DamageReportPersistenceAdapter(JpaDamageReportRepository damageReportRepository) {
        this.damageReportRepository = damageReportRepository;
    }

    @Override
    public DamageReport save(DamageReport report) {
        return mapper.toDomain(damageReportRepository.save(mapper.toEntity(report)));
    }

    @Override
    public Optional<DamageReport> findById(UUID id) {
        return damageReportRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<DamageReport> findAll() {
        return damageReportRepository.findAll().stream().map(mapper::toDomain).toList();
    }
}
