package com.dardan.rent_tool.infrastructure.adapters.out.persistence.adapter;

import com.dardan.rent_tool.application.port.out.RentalOutputPort;
import com.dardan.rent_tool.domain.model.entity.Rental;
import com.dardan.rent_tool.domain.model.valueobject.ToolIncome;
import com.dardan.rent_tool.domain.model.valueobject.ToolRentCount;
import com.dardan.rent_tool.infrastructure.adapters.out.persistence.mappers.RentalMapper;
import com.dardan.rent_tool.infrastructure.adapters.out.persistence.repository.JpaRentalRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class RentalPersistenceAdapter implements RentalOutputPort {

    private final JpaRentalRepository rentalRepository;
    private final RentalMapper mapper = new RentalMapper();

    public RentalPersistenceAdapter(JpaRentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Override
    public Rental save(Rental rental) {
        return mapper.toDomain(rentalRepository.save(mapper.toEntity(rental)));
    }

    @Override
    public Optional<Rental> findById(UUID id) {
        return rentalRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Rental> findAll() {
        return rentalRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<ToolRentCount> findTopRentedTools() {
        return rentalRepository.findTopRentedTools().stream()
            .map(row -> new ToolRentCount((UUID) row[0], (Long) row[1]))
            .toList();
    }

    @Override
    public List<ToolIncome> findToolIncome() {
        return rentalRepository.findToolIncome().stream()
            .map(row -> new ToolIncome((UUID) row[0], (java.math.BigDecimal) row[1]))
            .toList();
    }
}
