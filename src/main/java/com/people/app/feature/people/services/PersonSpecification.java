package com.people.app.feature.people.services;

import com.people.app.feature.people.entities.Person;
import org.springframework.data.jpa.domain.Specification;

public class PersonSpecification {

    public static Specification<Person> nameContains(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase().trim() + "%");
        };
    }

    public static Specification<Person> heightInRange(Double from, Double to) {
        return (root, query, criteriaBuilder) -> {
            if (from == null && to == null) {
                return criteriaBuilder.conjunction();
            } else if (from == null) {
                return criteriaBuilder.le(root.get("height"), to);
            } else if (to == null) {
                return criteriaBuilder.ge(root.get("height"), from);
            }
            return criteriaBuilder.between(root.get("height"), from, to);
        };
    }
}
