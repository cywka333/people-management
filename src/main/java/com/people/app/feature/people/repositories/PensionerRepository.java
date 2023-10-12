package com.people.app.feature.people.repositories;

import com.people.app.feature.people.entities.Pensioner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PensionerRepository extends JpaRepository<Pensioner, UUID> {
    Optional<Pensioner> findByUuid(UUID id);
}
