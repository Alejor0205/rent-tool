package com.dardan.rent_tool.application.usecase.rental;

import com.dardan.rent_tool.application.port.out.RentalOutputPort;
import com.dardan.rent_tool.domain.model.entity.Rental;
import com.dardan.rent_tool.domain.model.enumm.RentalStatus;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class ExpireRequestedRentalsUseCase {

    private final RentalOutputPort rentalOutputPort;

    public ExpireRequestedRentalsUseCase(RentalOutputPort rentalOutputPort) {
        this.rentalOutputPort = rentalOutputPort;
    }

    public int execute(OffsetDateTime cutoff) {
        List<Rental> expired = rentalOutputPort.findRequestedBefore(cutoff);
        for (Rental rental : expired) {
            rental.setStatus(RentalStatus.CANCELLED);
            rentalOutputPort.save(rental);
        }
        return expired.size();
    }
}
