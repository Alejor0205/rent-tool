package com.dardan.rent_tool.application.mapper;

import com.dardan.rent_tool.application.dto.PaymentDTO;
import com.dardan.rent_tool.domain.model.entity.Payment;

public class PaymentDTOMapper {
    public PaymentDTO toDTO(Payment payment) {
        if (payment == null) {
            return null;
        }
        return new PaymentDTO(
            payment.getId(),
            payment.getRentalId(),
            payment.getAmount(),
            payment.getStatus(),
            payment.getMethod(),
            payment.getCreatedAt()
        );
    }
}
