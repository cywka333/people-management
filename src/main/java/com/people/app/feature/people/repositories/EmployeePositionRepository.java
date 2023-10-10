package com.people.app.feature.people.repositories;

import com.people.app.feature.people.entities.EmployeePosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeePositionRepository extends JpaRepository<EmployeePosition, Long> {


    @Query("SELECT ep FROM EmployeePosition ep WHERE ep.employee.uuid = :employeeUuid AND (ep.startDate BETWEEN :startDate AND :endDate OR ep.endDate BETWEEN :startDate AND :endDate OR (:startDate BETWEEN ep.startDate AND ep.endDate OR :endDate BETWEEN ep.startDate AND ep.endDate))")
    List<EmployeePosition> findOverlappingPositions(@Param("employeeUuid") UUID employeeUuid, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
