package com.dardan.rent_tool.infrastructure.adapters.out.persistence.repository;

import com.dardan.rent_tool.infrastructure.adapters.out.persistence.entity.RentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface JpaRentalRepository extends JpaRepository<RentalEntity, UUID> {

    @Query("select r.toolId, count(r) from RentalEntity r group by r.toolId order by count(r) desc")
    List<Object[]> findTopRentedTools();

    @Query("select r.toolId, coalesce(sum(r.totalAmount), 0) from RentalEntity r group by r.toolId")
    List<Object[]> findToolIncome();
}
