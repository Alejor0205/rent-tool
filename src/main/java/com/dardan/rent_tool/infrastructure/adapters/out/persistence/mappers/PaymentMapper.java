package com.dardan.rent_tool.infrastructure.adapters.out.persistence.mappers;

import com.dardan.rent_tool.domain.model.entity.Payment;
import com.dardan.rent_tool.infrastructure.adapters.out.persistence.entity.PaymentEntity;

public class PaymentMapper {
    public Payment toDomain(PaymentEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Payment(
            entity.getId(),
            entity.getRentalId(),
            entity.getAmount(),
            entity.getStatus(),
            entity.getMethod(),
            entity.getCreatedAt()
        );
    }

    public PaymentEntity toEntity(Payment payment) {
        if (payment == null) {
            return null;
        }
        return new PaymentEntity(
            payment.getId(),
            payment.getRentalId(),
            payment.getAmount(),
            payment.getStatus(),
            payment.getMethod(),
            payment.getCreatedAt()
        );
    }
}
