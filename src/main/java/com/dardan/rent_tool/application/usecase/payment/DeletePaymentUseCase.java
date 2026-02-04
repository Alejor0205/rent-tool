package com.dardan.rent_tool.application.usecase.payment;

import com.dardan.rent_tool.application.exception.NotFoundException;
import com.dardan.rent_tool.application.port.out.PaymentOutputPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeletePaymentUseCase {

    private final PaymentOutputPort paymentOutputPort;

    public DeletePaymentUseCase(PaymentOutputPort paymentOutputPort) {
        this.paymentOutputPort = paymentOutputPort;
    }

    public void execute(UUID id) {
        if (paymentOutputPort.findById(id).isEmpty()) {
            throw new NotFoundException("paymentId", "El pago no existe.");
        }
        paymentOutputPort.deleteById(id);
    }
}
