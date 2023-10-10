package com.people.app.feature.people.entities;

import com.people.app.feature.people.dtos.PersonDTO;
import com.people.app.feature.people.enums.Gender;
import com.people.app.feature.people.types.PersonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import javax.naming.directory.InvalidAttributesException;
import java.util.Map;

@Entity
@Table(name = "students")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Student extends Person {

    @Column(name = "university_name")
    private String universityName;

    @Column(name = "scholarship")
    private double scholarship;

}
