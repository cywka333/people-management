package com.people.app.feature.people.factories;

import com.people.app.feature.people.entities.Pensioner;
import com.people.app.feature.people.entities.Person;
import org.springframework.stereotype.Service;

@Service
public class PensionerFactory implements PersonFactory {
    @Override
    public boolean supports(String type) {
        return "pensioner".equals(type);
    }

    @Override
    public Person createSpecificPersonType() {
        return new Pensioner();
    }
}
