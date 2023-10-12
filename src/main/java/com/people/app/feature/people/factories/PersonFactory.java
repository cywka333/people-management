package com.people.app.feature.people.factories;

import com.people.app.feature.people.entities.Person;

public interface PersonFactory {
    boolean supports(String type);

    Person createSpecificPersonType();
}
