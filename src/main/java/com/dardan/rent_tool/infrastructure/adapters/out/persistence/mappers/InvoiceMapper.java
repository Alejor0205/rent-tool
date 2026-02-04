package com.dardan.rent_tool.infrastructure.adapters.out.persistence.mappers;

import com.dardan.rent_tool.domain.model.entity.Invoice;
import com.dardan.rent_tool.infrastructure.adapters.out.persistence.entity.InvoiceEntity;

public class InvoiceMapper {
    public Invoice toDomain(InvoiceEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Invoice(entity.getId(), entity.getRentalId(), entity.getPdfData(), entity.getCreatedAt());
    }

    public InvoiceEntity toEntity(Invoice invoice) {
        if (invoice == null) {
            return null;
        }
        return new InvoiceEntity(
            invoice.getId(),
            invoice.getRentalId(),
            invoice.getPdfData(),
            invoice.getCreatedAt()
        );
    }
}
