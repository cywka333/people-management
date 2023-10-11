package com.people.app.feature.people.services;

import com.people.app.feature.people.entities.Person;
import com.people.app.feature.people.entities.Student;
import com.people.app.feature.people.repositories.StudentRepository;
import com.people.app.feature.people.types.PersonType;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;


@Service
public class StudentType implements PersonType {

    private final StudentRepository studentRepository;

    public StudentType(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public boolean supports(String type) {
        return "student".equals(type);
    }

    @Override
    public void savePerson(Person person) {
        if (person instanceof Student) {
            studentRepository.save((Student) person);
        }
    }

    @Override
    public void mapSpecificFields(Person person, CSVRecord record) {
        if (person instanceof Student) {
            Student student = (Student) person;
            // TODO Add specyfic logic for student
        }
    }

}
