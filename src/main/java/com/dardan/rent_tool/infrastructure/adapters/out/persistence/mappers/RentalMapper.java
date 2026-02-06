package com.dardan.rent_tool.infrastructure.adapters.out.persistence.mappers;

import java.util.UUID;

import com.dardan.rent_tool.domain.model.entity.Rental;
import com.dardan.rent_tool.infrastructure.adapters.out.persistence.entity.RentalEntity;
import com.dardan.rent_tool.infrastructure.adapters.out.persistence.entity.UserEntity;

public class RentalMapper {
    public Rental toDomain(RentalEntity entity) {
        if (entity == null) {
            return null;
        }
        UUID customerId = entity.getCustomer() != null ? entity.getCustomer().getId() : null;
        UUID providerId = entity.getProvider() != null ? entity.getProvider().getId() : null;
        return new Rental(
            entity.getId(),
            entity.getToolId(),
            customerId,
            providerId,
            entity.getStartDate(),
            entity.getEndDate(),
            entity.getTotalAmount(),
            entity.getStatus(),
            entity.getCreatedAt()
        );
    }

    public RentalEntity toEntity(Rental rental, UserEntity customer, UserEntity provider) {
        if (rental == null) {
            return null;
        }
        return new RentalEntity(
            rental.getId(),
            rental.getToolId(),
            customer,
            provider,
            rental.getStartDate(),
            rental.getEndDate(),
            rental.getTotalAmount(),
            rental.getStatus(),
            rental.getCreatedAt()
        );
    }
}
