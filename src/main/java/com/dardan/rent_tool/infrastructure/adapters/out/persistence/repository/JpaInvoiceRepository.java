package com.dardan.rent_tool.infrastructure.adapters.out.persistence.repository;

import com.dardan.rent_tool.infrastructure.adapters.out.persistence.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaInvoiceRepository extends JpaRepository<InvoiceEntity, UUID> {
    Optional<InvoiceEntity> findByRentalId(UUID rentalId);
}
