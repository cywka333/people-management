package com.people.app.feature.people.services;

import com.people.app.feature.people.types.PersonType;
import org.springframework.stereotype.Service;


@Service
public class StudentType implements PersonType {

    @Override
    public boolean supports(String type) {
        return "student".equals(type);
    }

//    @Override
//    public void validateAttributes(Map<String, Object> attributes) throws InvalidAttributesException {
//
//    }
}
