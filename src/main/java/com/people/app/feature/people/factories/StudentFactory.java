package com.people.app.feature.people.factories;

import com.people.app.feature.people.entities.Person;
import com.people.app.feature.people.entities.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentFactory implements PersonFactory {
    @Override
    public boolean supports(String type) {
        return "student".equals(type);
    }

    @Override
    public Person createSpecificPersonType() {
        return new Student();
    }
}
