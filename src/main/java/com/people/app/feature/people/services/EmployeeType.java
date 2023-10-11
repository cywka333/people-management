package com.people.app.feature.people.services;

import com.people.app.feature.people.entities.Employee;
import com.people.app.feature.people.entities.Person;
import com.people.app.feature.people.repositories.EmployeeRepository;
import com.people.app.feature.people.types.PersonType;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

@Service
public class EmployeeType implements PersonType {

    private final EmployeeRepository employeeRepository;

    public EmployeeType(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public boolean supports(String type) {
        return "employee".equals(type);
    }

    @Override
    public void savePerson(Person person) {
        if (person instanceof Employee) {
            employeeRepository.save((Employee) person);
        }
    }

    @Override
    public void mapSpecificFields(Person person, CSVRecord record) {
        if (person instanceof Employee) {
            Employee employee = (Employee) person;
            // TODO Add specyfic logic for employee
        }
    }
}
