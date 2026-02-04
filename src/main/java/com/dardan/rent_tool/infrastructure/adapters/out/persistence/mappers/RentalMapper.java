package com.dardan.rent_tool.infrastructure.adapters.out.persistence.mappers;

import com.dardan.rent_tool.domain.model.entity.Rental;
import com.dardan.rent_tool.infrastructure.adapters.out.persistence.entity.RentalEntity;

public class RentalMapper {
    public Rental toDomain(RentalEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Rental(
            entity.getId(),
            entity.getToolId(),
            entity.getCustomerId(),
            entity.getProviderId(),
            entity.getStartDate(),
            entity.getEndDate(),
            entity.getTotalAmount(),
            entity.getStatus(),
            entity.getCreatedAt()
        );
    }

    public RentalEntity toEntity(Rental rental) {
        if (rental == null) {
            return null;
        }
        return new RentalEntity(
            rental.getId(),
            rental.getToolId(),
            rental.getCustomerId(),
            rental.getProviderId(),
            rental.getStartDate(),
            rental.getEndDate(),
            rental.getTotalAmount(),
            rental.getStatus(),
            rental.getCreatedAt()
        );
    }
}
