package com.dardan.rent_tool.infrastructure.adapters.out.persistence.repository;

import com.dardan.rent_tool.domain.model.enumm.PaymentStatus;
import com.dardan.rent_tool.infrastructure.adapters.out.persistence.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.UUID;

public interface JpaPaymentRepository extends JpaRepository<PaymentEntity, UUID> {

    @Query("select coalesce(sum(p.amount), 0) from PaymentEntity p where p.status = ?1")
    BigDecimal sumAmountByStatus(PaymentStatus status);
}
