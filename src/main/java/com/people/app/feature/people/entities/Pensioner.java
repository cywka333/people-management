package com.people.app.feature.people.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Table(name = "pensioners")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Pensioner extends Person  {

    @Column(name = "pension")
    private double pension;

    @Column(name = "years_worked")
    private int yearsWorked;

}
