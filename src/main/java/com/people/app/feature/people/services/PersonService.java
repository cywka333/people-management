package com.people.app.feature.people.services;

import com.people.app.feature.people.dtos.PersonDTO;
import com.people.app.feature.people.entities.Person;
import com.people.app.feature.people.factories.PersonFactory;
import com.people.app.feature.people.filter.PersonFilter;
import com.people.app.feature.people.repositories.PersonRepository;
import com.people.app.feature.people.types.PersonType;
import com.people.app.feature.people.types.PersonTypeRegistry;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.naming.directory.InvalidAttributesException;
import java.util.List;
import java.util.UUID;

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

        return personRepository.save(person);
    }

    private Person createPersonByType(String type) {
        return personFactories.stream()
                .filter(factory -> factory.supports(type))
                .findFirst()
                .map(PersonFactory::createSpecificPersonType)
                .orElseThrow(() -> new IllegalArgumentException("Unsupported person type: " + type));
    }

    public Person editPerson(UUID id, PersonDTO personDTO){
        Person existingPerson = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No person with ID found:" + id));
        existingPerson.setName(personDTO.getName());
        existingPerson.setGender(personDTO.getGender());
        existingPerson.setHeight(personDTO.getHeight());

        return personRepository.save(existingPerson);
    }
}
