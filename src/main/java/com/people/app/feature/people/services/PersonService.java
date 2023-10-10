package com.people.app.feature.people.services;

import com.people.app.feature.people.dtos.PersonDTO;
import com.people.app.feature.people.entities.Person;
import com.people.app.feature.people.factories.PersonFactory;
import com.people.app.feature.people.filter.PersonFilter;
import com.people.app.feature.people.repositories.PersonRepository;
import com.people.app.feature.people.types.PersonType;
import com.people.app.feature.people.types.PersonTypeRegistry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.naming.directory.InvalidAttributesException;
import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonTypeRegistry personTypeRegistry;
    private final List<PersonFactory> personFactories;

    public PersonService(PersonRepository personRepository, PersonTypeRegistry personTypeRegistry, List<PersonFactory> personFactories) {
        this.personRepository = personRepository;
        this.personTypeRegistry = personTypeRegistry;
        this.personFactories = personFactories;
    }

    public Page<Person> search(PersonFilter criteria, Pageable pageable) {
        Specification<Person> spec = Specification.where(null);

        if (criteria.getName() != null && !criteria.getName().trim().isEmpty()) {
            spec = spec.and(PersonSpecification.nameContains(criteria.getName()));
        }

        if (criteria.getHeightFrom() != null || criteria.getHeightTo() != null) {
            spec = spec.and(PersonSpecification.heightInRange(criteria.getHeightFrom(), criteria.getHeightTo()));
        }

        return personRepository.findAll(spec, pageable);
    }


    public Person savePerson(PersonDTO dto) throws InvalidAttributesException {
        PersonType personType = personTypeRegistry.getPersonType(dto.getType());
        Person person = createPersonByType(dto.getType());
        person.setName(dto.getName());
        person.setHeight(dto.getHeight());
        person.setGender(dto.getGender());
//        person.setAttributes(dto.getAttributes());

        return personRepository.save(person);
    }

    private Person createPersonByType(String type) {
        return personFactories.stream()
                .filter(factory -> factory.supports(type))
                .findFirst()
                .map(PersonFactory::createPerson)
                .orElseThrow(() -> new IllegalArgumentException("Unsupported person type: " + type));
    }
}
