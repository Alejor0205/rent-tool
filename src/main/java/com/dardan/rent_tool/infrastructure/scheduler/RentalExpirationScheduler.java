package com.dardan.rent_tool.infrastructure.scheduler;

import com.dardan.rent_tool.application.usecase.rental.ExpireRequestedRentalsUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class RentalExpirationScheduler {

    private final ExpireRequestedRentalsUseCase expireRequestedRentalsUseCase;
    private final long expirationHours;

    public RentalExpirationScheduler(ExpireRequestedRentalsUseCase expireRequestedRentalsUseCase,
                                     @Value("${rental.request.expiration-hours}") long expirationHours) {
        this.expireRequestedRentalsUseCase = expireRequestedRentalsUseCase;
        this.expirationHours = expirationHours;
    }

    @Scheduled(fixedDelayString = "${rental.request.expiration-interval-ms:3600000}")
    public void expireOldRequests() {
        OffsetDateTime cutoff = OffsetDateTime.now().minusHours(expirationHours);
        expireRequestedRentalsUseCase.execute(cutoff);
    }
}
