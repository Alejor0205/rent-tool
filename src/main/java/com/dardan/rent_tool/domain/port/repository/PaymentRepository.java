package com.dardan.rent_tool.domain.port.repository;

import com.dardan.rent_tool.domain.model.entity.Payment;
import com.dardan.rent_tool.domain.model.enumm.PaymentStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository {
    Payment save(Payment payment);

    Optional<Payment> findById(UUID id);

    List<Payment> findAll();

    BigDecimal sumAmountByStatus(PaymentStatus status);

    void deleteById(UUID id);
}
