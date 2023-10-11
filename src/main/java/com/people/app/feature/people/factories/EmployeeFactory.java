package com.people.app.feature.people.factories;

import com.people.app.feature.people.entities.Employee;
import com.people.app.feature.people.entities.Person;
import org.springframework.stereotype.Service;

@Service
public class EmployeeFactory implements PersonFactory{
    @Override
    public boolean supports(String type) {
        return "employee".equals(type);
    }

    @Override
    public Person createSpecificPersonType() {
        return new Employee();
    }
}
