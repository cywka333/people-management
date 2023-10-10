package com.people.app.feature.people.services;

import com.people.app.feature.people.dtos.EmployeePositionDTO;
import com.people.app.feature.people.entities.Employee;
import com.people.app.feature.people.entities.EmployeePosition;
import com.people.app.feature.people.entities.Position;
import com.people.app.feature.people.repositories.EmployeePositionRepository;
import com.people.app.feature.people.repositories.EmployeeRepository;
import com.people.app.feature.people.repositories.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeePositionService {

    private final EmployeePositionRepository employeePositionRepository;
    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;

    public EmployeePositionService(EmployeePositionRepository employeePositionRepository, EmployeeRepository employeeRepository, PositionRepository positionRepository) {
        this.employeePositionRepository = employeePositionRepository;
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
    }

    public EmployeePositionDTO assignPositionToEmployee(EmployeePositionDTO dto){
        List<EmployeePosition> overlappingPositions = employeePositionRepository.findOverlappingPositions(dto.getEmployeeUuid(), dto.getStartDate(), dto.getEndDate());
        if(!overlappingPositions.isEmpty()){
            throw new IllegalArgumentException("The provided date range overlaps with an existing position.");
        }
        EmployeePosition position  = new EmployeePosition();
        position.setStartDate(dto.getStartDate());
        position.setEndDate(dto.getEndDate());
        position.setSalary(dto.getSalary());

        Optional<Employee> employeeOptional = employeeRepository.findByUuid(dto.getEmployeeUuid());
        Optional<Position> positionOptional = positionRepository.findById(dto.getPositionId());

        if (employeeOptional.isEmpty() || positionOptional.isEmpty()) {
            throw new IllegalArgumentException("Employee or Position not found.");
        }

        position.setEmployee(employeeOptional.get());
        position.setPosition(positionOptional.get());


        EmployeePosition savedPosition = employeePositionRepository.save(position);
        return new EmployeePositionDTO(savedPosition);
    }
}
