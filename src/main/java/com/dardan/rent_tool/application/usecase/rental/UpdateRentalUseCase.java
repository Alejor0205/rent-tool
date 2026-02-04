package com.dardan.rent_tool.application.usecase.rental;

import com.dardan.rent_tool.application.command.UpdateRentalCommand;
import com.dardan.rent_tool.application.dto.RentalDTO;
import com.dardan.rent_tool.application.exception.NotFoundException;
import com.dardan.rent_tool.application.mapper.RentalDTOMapper;
import com.dardan.rent_tool.application.port.out.RentalOutputPort;
import com.dardan.rent_tool.domain.model.entity.Rental;
import org.springframework.stereotype.Service;

@Service
public class UpdateRentalUseCase {

    private final RentalOutputPort rentalOutputPort;
    private final RentalDTOMapper mapper = new RentalDTOMapper();

    public UpdateRentalUseCase(RentalOutputPort rentalOutputPort) {
        this.rentalOutputPort = rentalOutputPort;
    }

    public RentalDTO execute(UpdateRentalCommand command) {
        Rental rental = rentalOutputPort.findById(command.getRentalId())
            .orElseThrow(() -> new NotFoundException("rentalId", "La reserva no existe."));

        if (command.getStartDate() != null) {
            rental.setStartDate(command.getStartDate());
        }
        if (command.getEndDate() != null) {
            rental.setEndDate(command.getEndDate());
        }
        if (command.getTotalAmount() != null) {
            rental.setTotalAmount(command.getTotalAmount());
        }

        return mapper.toDTO(rentalOutputPort.save(rental));
    }
}
