package com.people.app.feature.people.services;

import com.people.app.feature.people.dtos.PositionDTO;
import com.people.app.feature.people.entities.Position;
import com.people.app.feature.people.repositories.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PositionService {

    private final PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public PositionDTO createPosition(PositionDTO positionDTO) {
        Position position = new Position();
        position.setName(positionDTO.getName());
        Position savedPosition = positionRepository.save(position);
        return new PositionDTO(savedPosition.getId(), savedPosition.getName());
    }

    public List<PositionDTO> getAllPositions() {
        return positionRepository.findAll().stream()
                .map(position -> new PositionDTO(position.getId(), position.getName()))
                .collect(Collectors.toList());
    }
}
