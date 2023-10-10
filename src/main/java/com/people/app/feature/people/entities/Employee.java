package com.people.app.feature.people.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends Person {

    @Column(name = "employment_date")
    private LocalDate employmentStartDate;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<EmployeePosition> employeePositions = new ArrayList<>();

}
