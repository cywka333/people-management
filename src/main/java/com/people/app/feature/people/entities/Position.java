package com.people.app.feature.people.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "positions")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

}
