package com.people.app.feature.people.controllers;

import com.people.app.feature.people.dtos.PersonDTO;
import com.people.app.feature.people.entities.Person;
import com.people.app.feature.people.filter.PersonFilter;
import com.people.app.feature.people.services.PersonService;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.naming.directory.InvalidAttributesException;
import java.util.UUID;

@RestController
@RequestMapping("/api/people")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @PostMapping("/search")
    public Page<Person> search(@RequestBody PersonFilter criteria) {
        int page = (criteria.getPage() != null) ? criteria.getPage() : 0;
        int size = (criteria.getSize() != null) ? criteria.getSize() : 10;
        Pageable pageable = PageRequest.of(page, size);
        return personService.search(criteria, pageable);
    }

    @PostMapping("/add")
    public ResponseEntity<Person> createPerson(@RequestBody PersonDTO personDTO) throws InvalidAttributesException {
        Person person = personService.savePerson(personDTO);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Person> editPerson(@PathVariable UUID id, @RequestBody PersonDTO personDTO){
        try{
            Person updatedPerson = personService.editPerson(id, personDTO);
            return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
        } catch (OptimisticLockingFailureException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The data was changed by another transaction");
        }
    }
}