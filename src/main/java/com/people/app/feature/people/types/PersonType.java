package com.people.app.feature.people.types;


import com.people.app.feature.people.entities.Person;
import org.apache.commons.csv.CSVRecord;

public interface PersonType {
    boolean supports(String type);

    void savePerson(Person person);
    void mapSpecificFields(Person person, CSVRecord record);

}
