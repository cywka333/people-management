package com.people.app.feature.people.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;


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
