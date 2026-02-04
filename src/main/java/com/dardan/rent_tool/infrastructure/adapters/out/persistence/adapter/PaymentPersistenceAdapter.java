package com.dardan.rent_tool.infrastructure.adapters.out.persistence.adapter;

import com.dardan.rent_tool.application.port.out.PaymentOutputPort;
import com.dardan.rent_tool.domain.model.entity.Payment;
import com.dardan.rent_tool.domain.model.enumm.PaymentStatus;
import com.dardan.rent_tool.infrastructure.adapters.out.persistence.mappers.PaymentMapper;
import com.dardan.rent_tool.infrastructure.adapters.out.persistence.repository.JpaPaymentRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class PaymentPersistenceAdapter implements PaymentOutputPort {

    private final JpaPaymentRepository paymentRepository;
    private final PaymentMapper mapper = new PaymentMapper();

    public PaymentPersistenceAdapter(JpaPaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment save(Payment payment) {
        return mapper.toDomain(paymentRepository.save(mapper.toEntity(payment)));
    }

    @Override
    public Optional<Payment> findById(UUID id) {
        return paymentRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public BigDecimal sumAmountByStatus(PaymentStatus status) {
        return paymentRepository.sumAmountByStatus(status);
    }
}
