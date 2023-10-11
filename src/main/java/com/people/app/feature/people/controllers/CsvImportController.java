package com.people.app.feature.people.controllers;

import com.people.app.feature.people.entities.ImportJob;
import com.people.app.feature.people.services.CsvImportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/import")
public class CsvImportController {

    private final CsvImportService csvImportService;

    public CsvImportController(CsvImportService csvImportService) {
        this.csvImportService = csvImportService;
    }

    @GetMapping("/status")
    public ResponseEntity<ImportJob> getImportStatus(){
       return ResponseEntity.of(csvImportService.getLatestImportStatus());
    }

    @PostMapping
    public ResponseEntity<?> uploadCsv(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        if (csvImportService.isImportInProgress()) {
            return ResponseEntity.badRequest().body("Import is already in progress.");
        }

        csvImportService.importCsv(multipartFile);
        return ResponseEntity.ok().body("Import started.");
    }

}
