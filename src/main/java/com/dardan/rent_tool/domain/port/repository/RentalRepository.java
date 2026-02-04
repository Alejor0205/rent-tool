package com.dardan.rent_tool.domain.port.repository;

import com.dardan.rent_tool.domain.model.entity.Rental;

import com.dardan.rent_tool.domain.model.valueobject.ToolIncome;
import com.dardan.rent_tool.domain.model.valueobject.ToolRentCount;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RentalRepository {
    Rental save(Rental rental);

    Optional<Rental> findById(UUID id);

    List<Rental> findAll();

    List<ToolRentCount> findTopRentedTools();

    List<ToolIncome> findToolIncome();

    List<Rental> findByCustomerId(UUID customerId);

    void deleteById(UUID id);

    boolean existsOverlap(UUID toolId,
                          java.time.LocalDate startDate,
                          java.time.LocalDate endDate,
                          java.util.List<com.dardan.rent_tool.domain.model.enumm.RentalStatus> statuses);

    boolean existsOverlapExcluding(UUID toolId,
                                   UUID excludeId,
                                   java.time.LocalDate startDate,
                                   java.time.LocalDate endDate,
                                   java.util.List<com.dardan.rent_tool.domain.model.enumm.RentalStatus> statuses);

    java.util.List<Rental> findRequestedBefore(java.time.OffsetDateTime cutoff);
}
