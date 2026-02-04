package com.dardan.rent_tool.application.mapper;

import com.dardan.rent_tool.application.dto.RentalDTO;
import com.dardan.rent_tool.domain.model.entity.Rental;

public class RentalDTOMapper {
    public RentalDTO toDTO(Rental rental) {
        if (rental == null) {
            return null;
        }
        return new RentalDTO(
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
