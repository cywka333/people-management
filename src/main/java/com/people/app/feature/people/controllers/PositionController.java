package com.people.app.feature.people.controllers;

import com.people.app.feature.people.dtos.PositionDTO;
import com.people.app.feature.people.services.PositionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
public class PositionController {

    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @PostMapping
    public PositionDTO createPosition(@RequestBody PositionDTO positionDTO){
        return positionService.createPosition(positionDTO);
    }

    @GetMapping
    public List<PositionDTO> getAllPositions(){
        return positionService.getAllPositions();
    }
}
