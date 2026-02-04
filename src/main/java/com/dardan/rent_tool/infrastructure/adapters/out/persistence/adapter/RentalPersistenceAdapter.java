package com.dardan.rent_tool.infrastructure.adapters.out.persistence.adapter;

import com.dardan.rent_tool.application.port.out.RentalOutputPort;
import com.dardan.rent_tool.domain.model.entity.Rental;
import com.dardan.rent_tool.domain.model.valueobject.ToolIncome;
import com.dardan.rent_tool.domain.model.valueobject.ToolRentCount;
import com.dardan.rent_tool.infrastructure.adapters.out.persistence.entity.UserEntity;
import com.dardan.rent_tool.infrastructure.adapters.out.persistence.mappers.RentalMapper;
import com.dardan.rent_tool.infrastructure.adapters.out.persistence.repository.JpaRentalRepository;
import com.dardan.rent_tool.infrastructure.adapters.out.persistence.repository.JpaUserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class RentalPersistenceAdapter implements RentalOutputPort {

    private final JpaRentalRepository rentalRepository;
    private final JpaUserRepository userRepository;
    private final RentalMapper mapper = new RentalMapper();

    public RentalPersistenceAdapter(JpaRentalRepository rentalRepository, JpaUserRepository userRepository) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Rental save(Rental rental) {
        UserEntity customer = null;
        UserEntity provider = null;
        if (rental.getCustomerId() != null) {
            customer = userRepository.findById(rental.getCustomerId()).orElseThrow();
        }
        if (rental.getProviderId() != null) {
            provider = userRepository.findById(rental.getProviderId()).orElseThrow();
        }
        return mapper.toDomain(rentalRepository.save(mapper.toEntity(rental, customer, provider)));
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

    @Override
    public List<Rental> findByCustomerId(UUID customerId) {
        return rentalRepository.findByCustomer_Id(customerId).stream()
            .map(mapper::toDomain)
            .toList();
    }

    @Override
    public void deleteById(UUID id) {
        rentalRepository.deleteById(id);
    }

    @Override
    public boolean existsOverlap(UUID toolId,
                                 java.time.LocalDate startDate,
                                 java.time.LocalDate endDate,
                                 java.util.List<com.dardan.rent_tool.domain.model.enumm.RentalStatus> statuses) {
        return rentalRepository.existsOverlap(toolId, startDate, endDate, statuses);
    }

    @Override
    public boolean existsOverlapExcluding(UUID toolId,
                                          UUID excludeId,
                                          java.time.LocalDate startDate,
                                          java.time.LocalDate endDate,
                                          java.util.List<com.dardan.rent_tool.domain.model.enumm.RentalStatus> statuses) {
        return rentalRepository.existsOverlapExcluding(toolId, excludeId, startDate, endDate, statuses);
    }

    @Override
    public java.util.List<Rental> findRequestedBefore(java.time.OffsetDateTime cutoff) {
        return rentalRepository.findByStatusAndCreatedAtBefore(
                com.dardan.rent_tool.domain.model.enumm.RentalStatus.REQUESTED, cutoff)
            .stream()
            .map(mapper::toDomain)
            .toList();
    }
}
