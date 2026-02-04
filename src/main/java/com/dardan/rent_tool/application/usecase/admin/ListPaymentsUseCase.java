package com.dardan.rent_tool.application.usecase.admin;

import com.dardan.rent_tool.application.dto.PaymentDTO;
import com.dardan.rent_tool.application.mapper.PaymentDTOMapper;
import com.dardan.rent_tool.application.port.out.PaymentOutputPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListPaymentsUseCase {

    private final PaymentOutputPort paymentOutputPort;
    private final PaymentDTOMapper mapper = new PaymentDTOMapper();

    public ListPaymentsUseCase(PaymentOutputPort paymentOutputPort) {
        this.paymentOutputPort = paymentOutputPort;
    }

    public List<PaymentDTO> execute() {
        return paymentOutputPort.findAll().stream().map(mapper::toDTO).toList();
    }
}
