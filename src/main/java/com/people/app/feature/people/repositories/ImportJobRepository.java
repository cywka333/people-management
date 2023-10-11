package com.people.app.feature.people.repositories;

import com.people.app.feature.people.entities.ImportJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImportJobRepository extends JpaRepository<ImportJob, Long> {
    Optional<ImportJob> findTopByOrderByCreatedDateDesc();
}
