package com.people.app.feature.people.filter;

import com.people.app.feature.people.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PersonFilter {
    private String name;
    private String nameContains;
    private Double heightFrom;
    private Double heightTo;
    private Gender gender;
    private LocalDate employmentDateFrom;
    private LocalDate employmentDateTo;
    private String universityNameContains;
    private Double scholarshipFrom;
    private Double scholarshipTo;
    private Double pensionFrom;
    private Double pensionTo;
    private Integer yearsWorkedFrom;
    private Integer yearsWorkedTo;
    private Integer page;
    private Integer size;
}
