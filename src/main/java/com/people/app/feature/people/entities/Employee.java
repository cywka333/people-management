package com.people.app.feature.people.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends Person {

    @Temporal(TemporalType.DATE)
    @Column(name = "employment_date")
    private LocalDate employmentStartDate;

}
