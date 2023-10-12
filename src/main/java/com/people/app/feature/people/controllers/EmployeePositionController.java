package com.people.app.feature.people.controllers;

import com.people.app.feature.people.dtos.EmployeePositionDTO;
import com.people.app.feature.people.services.EmployeePositionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/employees/{employeeUuid}/positions")
public class EmployeePositionController {

    private final EmployeePositionService employeePositionService;

    public EmployeePositionController(EmployeePositionService employeePositionService) {
        this.employeePositionService = employeePositionService;
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    @PostMapping
    public EmployeePositionDTO assignPositionToEmployee(@PathVariable UUID employeeUuid, @RequestBody EmployeePositionDTO positionDTO) {
        positionDTO.setEmployeeUuid(employeeUuid);
        return employeePositionService.assignPositionToEmployee(positionDTO);
    }
}
