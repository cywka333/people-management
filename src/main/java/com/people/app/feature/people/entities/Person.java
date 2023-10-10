package com.people.app.feature.people.entities;

import com.people.app.feature.people.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.util.Map;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "people")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "height")
    private double height;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Version
    private Long version;

//    @Type(JsonType.class)
//    @Column(columnDefinition = "jsonb")
//    private Map<String, Object> attributes;

}
