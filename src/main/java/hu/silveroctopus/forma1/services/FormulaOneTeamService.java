/*
 * Copyright (c) 2023. Created by Andras Laczo.
 */

package hu.silveroctopus.forma1.services;

import hu.silveroctopus.forma1.dto.FormulaOneTeamDto;
import hu.silveroctopus.forma1.model.FormulaOneTeam;
import hu.silveroctopus.forma1.repository.FormulaOneTeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FormulaOneTeamService {

    private final FormulaOneTeamRepository formulaOneTeamRepository;

    public List<FormulaOneTeamDto> getAllTeams() {
        return formulaOneTeamRepository.findAll().stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public Optional<FormulaOneTeamDto> getFormulaOneTeamById(Long id) {
        return formulaOneTeamRepository.findById(id)
                .map(this::mapEntityToDto);
    }

    @Transactional
    public FormulaOneTeamDto saveFormulaOneTeam(FormulaOneTeamDto formulaOneTeamDto) {
        FormulaOneTeam formulaOneTeamToSave = mapDtoToEntity(formulaOneTeamDto);
        FormulaOneTeam savedEntity = formulaOneTeamRepository.save(formulaOneTeamToSave);
        return mapEntityToDto(savedEntity);
    }

    public void deleteFormulaOneTeam(Long id) {
        formulaOneTeamRepository.deleteById(id);
    }

    private FormulaOneTeamDto mapEntityToDto(FormulaOneTeam formulaOneTeamEntity) {
        return FormulaOneTeamDto.builder()
                .id(formulaOneTeamEntity.getId())
                .name(formulaOneTeamEntity.getName())
                .foundationYear(formulaOneTeamEntity.getFoundationYear())
                .worldChampionshipsWon(formulaOneTeamEntity.getWorldChampionshipsWon())
                .entryFeePaid(formulaOneTeamEntity.isEntryFeePaid())
                .build();
    }

    private FormulaOneTeam mapDtoToEntity(FormulaOneTeamDto formulaOneTeamDto) {
        return FormulaOneTeam.builder()
                .id(formulaOneTeamDto.id())
                .name(formulaOneTeamDto.name())
                .foundationYear(formulaOneTeamDto.foundationYear())
                .worldChampionshipsWon(formulaOneTeamDto.worldChampionshipsWon())
                .entryFeePaid(formulaOneTeamDto.entryFeePaid())
                .build();
    }
}
