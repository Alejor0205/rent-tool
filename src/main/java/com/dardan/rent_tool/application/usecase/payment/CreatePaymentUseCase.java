package com.dardan.rent_tool.application.usecase.payment;

import com.dardan.rent_tool.application.dto.PaymentDTO;
import com.dardan.rent_tool.application.exception.NotFoundException;
import com.dardan.rent_tool.application.exception.ValidationException;
import com.dardan.rent_tool.application.mapper.PaymentDTOMapper;
import com.dardan.rent_tool.application.port.out.InvoiceOutputPort;
import com.dardan.rent_tool.application.port.out.PaymentOutputPort;
import com.dardan.rent_tool.application.port.out.RentalOutputPort;
import com.dardan.rent_tool.domain.model.entity.Payment;
import com.dardan.rent_tool.domain.model.entity.Rental;
import com.dardan.rent_tool.domain.model.entity.Invoice;
import com.dardan.rent_tool.domain.model.enumm.PaymentStatus;
import com.dardan.rent_tool.domain.model.enumm.RentalStatus;
import com.dardan.rent_tool.application.usecase.rental.GenerateInvoiceUseCase;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class CreatePaymentUseCase {

    private final PaymentOutputPort paymentOutputPort;
    private final RentalOutputPort rentalOutputPort;
    private final InvoiceOutputPort invoiceOutputPort;
    private final GenerateInvoiceUseCase generateInvoiceUseCase;
    private final PaymentDTOMapper mapper = new PaymentDTOMapper();

    public CreatePaymentUseCase(PaymentOutputPort paymentOutputPort,
                                RentalOutputPort rentalOutputPort,
                                InvoiceOutputPort invoiceOutputPort,
                                GenerateInvoiceUseCase generateInvoiceUseCase) {
        this.paymentOutputPort = paymentOutputPort;
        this.rentalOutputPort = rentalOutputPort;
        this.invoiceOutputPort = invoiceOutputPort;
        this.generateInvoiceUseCase = generateInvoiceUseCase;
    }

    public PaymentDTO execute(UUID rentalId, UUID customerId, String method) {
        Rental rental = rentalOutputPort.findById(rentalId)
            .orElseThrow(() -> new NotFoundException("rentalId", "La reserva no existe."));
        if (rental.getCustomerId() == null || !rental.getCustomerId().equals(customerId)) {
            throw new ValidationException("rental.customerId", "No autorizado para pagar esta reserva.");
        }
        if (rental.getStatus() == RentalStatus.REJECTED || rental.getStatus() == RentalStatus.CANCELLED) {
            throw new ValidationException("rental.status", "La reserva no es pagable.");
        }

        Payment payment = new Payment(
            null,
            rental.getId(),
            rental.getTotalAmount(),
            PaymentStatus.PAID,
            method == null ? "MOCK" : method,
            OffsetDateTime.now()
        );
        rental.setStatus(RentalStatus.ACTIVE);
        rentalOutputPort.save(rental);
        Payment saved = paymentOutputPort.save(payment);

        if (invoiceOutputPort.findByRentalId(rental.getId()).isEmpty()) {
            byte[] pdf = generateInvoiceUseCase.execute(rental.getId());
            invoiceOutputPort.save(new Invoice(null, rental.getId(), pdf, OffsetDateTime.now()));
        }

        return mapper.toDTO(saved);
    }
}
