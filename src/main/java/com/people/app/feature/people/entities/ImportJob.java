package com.people.app.feature.people.entities;

import com.people.app.feature.people.enums.ImportStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ImportJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ImportStatus importStatus;

    private LocalDateTime createdDate;

    private LocalDateTime startDate;

    private Long processedRows;


}
