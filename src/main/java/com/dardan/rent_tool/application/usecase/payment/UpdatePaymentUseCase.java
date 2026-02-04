package com.dardan.rent_tool.application.usecase.payment;

import com.dardan.rent_tool.application.command.UpdatePaymentCommand;
import com.dardan.rent_tool.application.dto.PaymentDTO;
import com.dardan.rent_tool.application.exception.NotFoundException;
import com.dardan.rent_tool.application.mapper.PaymentDTOMapper;
import com.dardan.rent_tool.application.port.out.PaymentOutputPort;
import com.dardan.rent_tool.domain.model.entity.Payment;
import org.springframework.stereotype.Service;

@Service
public class UpdatePaymentUseCase {

    private final PaymentOutputPort paymentOutputPort;
    private final PaymentDTOMapper mapper = new PaymentDTOMapper();

    public UpdatePaymentUseCase(PaymentOutputPort paymentOutputPort) {
        this.paymentOutputPort = paymentOutputPort;
    }

    public PaymentDTO execute(UpdatePaymentCommand command) {
        Payment payment = paymentOutputPort.findById(command.getPaymentId())
            .orElseThrow(() -> new NotFoundException("paymentId", "El pago no existe."));

        if (command.getStatus() != null) {
            payment.setStatus(command.getStatus());
        }
        if (command.getMethod() != null && !command.getMethod().isBlank()) {
            payment.setMethod(command.getMethod());
        }

        return mapper.toDTO(paymentOutputPort.save(payment));
    }
}
