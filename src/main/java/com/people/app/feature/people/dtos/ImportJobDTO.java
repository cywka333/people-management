package com.people.app.feature.people.dtos;

import com.people.app.feature.people.enums.ImportStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ImportJobDTO {
    private Long id;
    private ImportStatus importStatus;
    private LocalDateTime createdDate;
    private LocalDateTime startDate;
    private Long processedRows;
}
