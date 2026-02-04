package com.dardan.rent_tool.infrastructure.adapters.out.persistence.repository;

import com.dardan.rent_tool.infrastructure.adapters.out.persistence.entity.RentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface JpaRentalRepository extends JpaRepository<RentalEntity, UUID> {

    @Query("select r.toolId, count(r) from RentalEntity r group by r.toolId order by count(r) desc")
    List<Object[]> findTopRentedTools();

    @Query("select r.toolId, coalesce(sum(r.totalAmount), 0) from RentalEntity r group by r.toolId")
    List<Object[]> findToolIncome();

    List<RentalEntity> findByCustomer_Id(UUID customerId);

    @Query("select count(r) > 0 from RentalEntity r " +
           "where r.toolId = :toolId " +
           "and r.status in :statuses " +
           "and r.startDate <= :endDate " +
           "and r.endDate >= :startDate")
    boolean existsOverlap(@Param("toolId") UUID toolId,
                          @Param("startDate") java.time.LocalDate startDate,
                          @Param("endDate") java.time.LocalDate endDate,
                          @Param("statuses") List<com.dardan.rent_tool.domain.model.enumm.RentalStatus> statuses);

    @Query("select count(r) > 0 from RentalEntity r " +
           "where r.toolId = :toolId " +
           "and r.id <> :excludeId " +
           "and r.status in :statuses " +
           "and r.startDate <= :endDate " +
           "and r.endDate >= :startDate")
    boolean existsOverlapExcluding(@Param("toolId") UUID toolId,
                                   @Param("excludeId") UUID excludeId,
                                   @Param("startDate") java.time.LocalDate startDate,
                                   @Param("endDate") java.time.LocalDate endDate,
                                   @Param("statuses") List<com.dardan.rent_tool.domain.model.enumm.RentalStatus> statuses);

    List<RentalEntity> findByStatusAndCreatedAtBefore(com.dardan.rent_tool.domain.model.enumm.RentalStatus status,
                                                      java.time.OffsetDateTime createdAt);
}
