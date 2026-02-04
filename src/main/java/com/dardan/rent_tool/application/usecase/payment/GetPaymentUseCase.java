package com.dardan.rent_tool.application.usecase.payment;

import com.dardan.rent_tool.application.dto.PaymentDTO;
import com.dardan.rent_tool.application.exception.NotFoundException;
import com.dardan.rent_tool.application.mapper.PaymentDTOMapper;
import com.dardan.rent_tool.application.port.out.PaymentOutputPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetPaymentUseCase {

    private final PaymentOutputPort paymentOutputPort;
    private final PaymentDTOMapper mapper = new PaymentDTOMapper();

    public GetPaymentUseCase(PaymentOutputPort paymentOutputPort) {
        this.paymentOutputPort = paymentOutputPort;
    }

    public PaymentDTO execute(UUID id) {
        return paymentOutputPort.findById(id)
            .map(mapper::toDTO)
            .orElseThrow(() -> new NotFoundException("paymentId", "El pago no existe."));
    }
}
