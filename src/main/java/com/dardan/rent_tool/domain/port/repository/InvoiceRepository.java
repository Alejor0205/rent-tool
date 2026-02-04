package com.dardan.rent_tool.domain.port.repository;

import com.dardan.rent_tool.domain.model.entity.Invoice;

import java.util.Optional;
import java.util.UUID;

public interface InvoiceRepository {
    Invoice save(Invoice invoice);

    Optional<Invoice> findByRentalId(UUID rentalId);
}
