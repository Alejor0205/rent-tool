package com.dardan.rent_tool.application.usecase.rental;

import com.dardan.rent_tool.application.command.UpdateRentalStatusCommand;
import com.dardan.rent_tool.application.dto.RentalDTO;
import com.dardan.rent_tool.application.exception.NotFoundException;
import com.dardan.rent_tool.application.exception.ValidationException;
import com.dardan.rent_tool.application.mapper.RentalDTOMapper;
import com.dardan.rent_tool.application.port.out.RentalOutputPort;
import com.dardan.rent_tool.domain.model.entity.Rental;
import com.dardan.rent_tool.domain.model.enumm.RentalStatus;
import org.springframework.stereotype.Service;

@Service
public class RejectRentalUseCase {

    private final RentalOutputPort rentalOutputPort;
    private final RentalDTOMapper mapper = new RentalDTOMapper();

    public RejectRentalUseCase(RentalOutputPort rentalOutputPort) {
        this.rentalOutputPort = rentalOutputPort;
    }

    public RentalDTO execute(UpdateRentalStatusCommand command) {
        Rental rental = rentalOutputPort.findById(command.getRentalId())
            .orElseThrow(() -> new NotFoundException("rentalId", "La reserva no existe."));
        if (rental.getStatus() != RentalStatus.REQUESTED) {
            throw new ValidationException("rental.status", "Solo se pueden rechazar reservas en estado REQUESTED.");
        }
        rental.setStatus(RentalStatus.REJECTED);
        return mapper.toDTO(rentalOutputPort.save(rental));
    }
}
