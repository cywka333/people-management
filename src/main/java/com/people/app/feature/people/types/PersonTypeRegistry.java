package com.people.app.feature.people.types;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonTypeRegistry {

    private final List<PersonType> personTypes;

    public PersonTypeRegistry(List<PersonType> personTypes) {
        this.personTypes = personTypes;
    }

    public PersonType getPersonType(String type) {
        return personTypes.stream()
                .filter(t -> t.supports(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported person type: " + type));
    }
}
