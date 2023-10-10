package com.people.app.feature.people.dtos;

import com.people.app.feature.people.entities.EmployeePosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class EmployeePositionDTO {

    private Long id;
    private UUID employeeUuid;
    private Long positionId;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal salary;


    public EmployeePositionDTO(EmployeePosition employeePosition) {
        this.id = employeePosition.getId();
        this.employeeUuid = employeePosition.getEmployee().getUuid();
        this.positionId = employeePosition.getPosition().getId();
        this.startDate = employeePosition.getStartDate();
        this.endDate = employeePosition.getEndDate();
        this.salary = employeePosition.getSalary();
    }
}
