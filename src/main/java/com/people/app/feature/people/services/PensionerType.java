package com.people.app.feature.people.services;

import com.people.app.feature.people.entities.Pensioner;
import com.people.app.feature.people.entities.Person;
import com.people.app.feature.people.repositories.PensionerRepository;
import com.people.app.feature.people.types.PersonType;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;


@Service
public class PensionerType implements PersonType {

    private final PensionerRepository pensionerRepository;

    public PensionerType(PensionerRepository pensionerRepository) {
        this.pensionerRepository = pensionerRepository;
    }

    @Override
    public boolean supports(String type) {
        return "pensioner".equals(type);
    }

    @Override
    public void savePerson(Person person) {
        if (person instanceof Pensioner) {
            pensionerRepository.save((Pensioner) person);
        }
    }

    @Override
    public void mapSpecificFields(Person person, CSVRecord record) {
        if (person instanceof Pensioner) {
            Pensioner pensioner = (Pensioner) person;
            // TODO Add specyfic logic for pensioner
        }
    }

}
