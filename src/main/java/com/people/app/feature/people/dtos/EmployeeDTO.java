package com.people.app.feature.people.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class EmployeeDTO extends PersonDTO {

    private LocalDate employmentStartDate;
    private List<EmployeePositionDTO> employeePositions;

}
