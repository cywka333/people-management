package com.people.app.feature.people.services;

import com.people.app.feature.people.entities.ImportJob;
import com.people.app.feature.people.entities.Person;  // Zakładam, że masz taką klasę
import com.people.app.feature.people.enums.ImportStatus;
import com.people.app.feature.people.factories.PersonFactory;
import com.people.app.feature.people.repositories.ImportJobRepository;
import com.people.app.feature.people.types.PersonType;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CsvImportService {

    private final ImportJobRepository importJobRepository;
    private final List<PersonFactory> personFactories;
    private final List<PersonType> personTypes;


    public CsvImportService(ImportJobRepository importJobRepository, List<PersonFactory> personFactories, List<PersonType> personTypes) {
        this.importJobRepository = importJobRepository;
        this.personFactories = personFactories;
        this.personTypes = personTypes;
    }

    @Async
    public void importCsv(MultipartFile multipartFile) throws Exception {
        ImportJob importJob = new ImportJob();
        importJob.setImportStatus(ImportStatus.IN_PROGRESS);
        importJob.setStartDate(LocalDateTime.now());
        importJobRepository.save(importJob);

        try (Reader reader = new InputStreamReader(multipartFile.getInputStream());
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                     .withFirstRecordAsHeader()
                     .withIgnoreHeaderCase()
                     .withTrim());) {

            for (CSVRecord record : csvParser) {
                Person person = createPersonFromRecord(record);
                savePersonToDatabase(person, record.get("TYP osoby"));
            }

            importJob.setImportStatus(ImportStatus.COMPLETED);
            importJobRepository.save(importJob);
        }
    }

    private Person createPersonFromRecord(CSVRecord record) {
        String personType = record.get("TYP osoby");
        Person person = null;

        for (PersonType typeService : personTypes) {
            if (typeService.supports(personType)) {
                for (PersonFactory factory : personFactories) {
                    if (factory.supports(personType)) {
                        person = factory.createSpecificPersonType();

                        // Mapowanie danych
                        person.setName(record.get("imię"));
                        person.setHeight(Double.parseDouble(record.get("wzrost")));
                        // Dodaj inne pola mapowania jak potrzebujesz

                        // Mapowanie specyficznych danych dla konkretnej klasy osoby
                        typeService.mapSpecificFields(person, record);

                        return person;
                    }
                }
            }
        }
        return person;
    }

    private void savePersonToDatabase(Person person, String personType) {
        for (PersonType typeService : personTypes) {
            if (typeService.supports(personType)) {
                typeService.savePerson(person);
                break;
            }
        }
    }

    public Optional<ImportJob> getLatestImportStatus() {
        return importJobRepository.findTopByOrderByCreatedDateDesc();
    }

    public boolean isImportInProgress() {
        Optional<ImportJob> latestJob = getLatestImportStatus();
        return latestJob.isPresent() && latestJob.get().getImportStatus() == ImportStatus.IN_PROGRESS;
    }
}