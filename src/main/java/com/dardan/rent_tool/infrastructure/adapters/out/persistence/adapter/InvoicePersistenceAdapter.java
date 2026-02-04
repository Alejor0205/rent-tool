package com.dardan.rent_tool.infrastructure.adapters.out.persistence.adapter;

import com.dardan.rent_tool.application.port.out.InvoiceOutputPort;
import com.dardan.rent_tool.domain.model.entity.Invoice;
import com.dardan.rent_tool.infrastructure.adapters.out.persistence.mappers.InvoiceMapper;
import com.dardan.rent_tool.infrastructure.adapters.out.persistence.repository.JpaInvoiceRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class InvoicePersistenceAdapter implements InvoiceOutputPort {

    private final JpaInvoiceRepository invoiceRepository;
    private final InvoiceMapper mapper = new InvoiceMapper();

    public InvoicePersistenceAdapter(JpaInvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice save(Invoice invoice) {
        return mapper.toDomain(invoiceRepository.save(mapper.toEntity(invoice)));
    }

    @Override
    public Optional<Invoice> findByRentalId(UUID rentalId) {
        return invoiceRepository.findByRentalId(rentalId).map(mapper::toDomain);
    }
}
