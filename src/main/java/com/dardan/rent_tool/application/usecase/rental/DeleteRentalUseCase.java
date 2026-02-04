package com.dardan.rent_tool.application.usecase.rental;

import com.dardan.rent_tool.application.exception.NotFoundException;
import com.dardan.rent_tool.application.port.out.RentalOutputPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteRentalUseCase {

    private final RentalOutputPort rentalOutputPort;

    public DeleteRentalUseCase(RentalOutputPort rentalOutputPort) {
        this.rentalOutputPort = rentalOutputPort;
    }

    public void execute(UUID id) {
        if (rentalOutputPort.findById(id).isEmpty()) {
            throw new NotFoundException("rentalId", "La reserva no existe.");
        }
        rentalOutputPort.deleteById(id);
    }
}
