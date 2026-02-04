package com.dardan.rent_tool.application.usecase.rental;

import com.dardan.rent_tool.application.exception.NotFoundException;
import com.dardan.rent_tool.application.port.out.RentalOutputPort;
import com.dardan.rent_tool.application.port.out.ToolOutputPort;
import com.dardan.rent_tool.application.port.out.UserOutputPort;
import com.dardan.rent_tool.domain.model.entity.RentTool;
import com.dardan.rent_tool.domain.model.entity.Rental;
import com.dardan.rent_tool.domain.model.entity.User;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class GenerateInvoiceUseCase {

    private final RentalOutputPort rentalOutputPort;
    private final ToolOutputPort toolOutputPort;
    private final UserOutputPort userOutputPort;

    public GenerateInvoiceUseCase(RentalOutputPort rentalOutputPort,
                                  ToolOutputPort toolOutputPort,
                                  UserOutputPort userOutputPort) {
        this.rentalOutputPort = rentalOutputPort;
        this.toolOutputPort = toolOutputPort;
        this.userOutputPort = userOutputPort;
    }

    public byte[] execute(UUID rentalId) {
        Rental rental = rentalOutputPort.findById(rentalId)
            .orElseThrow(() -> new NotFoundException("rentalId", "La reserva no existe."));
        RentTool tool = toolOutputPort.findById(rental.getToolId())
            .orElseThrow(() -> new NotFoundException("toolId", "La herramienta no existe."));
        User customer = userOutputPort.findById(rental.getCustomerId())
            .orElseThrow(() -> new NotFoundException("customerId", "El cliente no existe."));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();

        document.add(new Paragraph("Factura de Alquiler"));
        document.add(new Paragraph("Cliente: " + customer.getFullName()));
        document.add(new Paragraph("Email: " + customer.getEmail()));
        document.add(new Paragraph("Herramienta: " + tool.getName()));
        document.add(new Paragraph("Fechas: " + formatDate(rental.getStartDate()) + " a " + formatDate(rental.getEndDate())));
        document.add(new Paragraph("Monto total: " + rental.getTotalAmount()));
        document.add(new Paragraph("Reserva ID: " + rental.getId()));

        document.close();
        return outputStream.toByteArray();
    }

    private String formatDate(java.time.LocalDate date) {
        if (date == null) {
            return "-";
        }
        return date.format(DateTimeFormatter.ISO_DATE);
    }
}
