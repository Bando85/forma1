/*
 * Copyright (c) 2023. Created by Andras Laczo.
 */

package hu.silveroctopus.forma1.controller;

import hu.silveroctopus.forma1.dto.FormulaOneTeamDto;
import hu.silveroctopus.forma1.services.FormulaOneTeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FormulaOneTeamController {

    private final Logger log = LoggerFactory.getLogger(FormulaOneTeamController.class);

    private final FormulaOneTeamService formulaOneTeamService;

    @GetMapping("/formulaoneteams")
    public List<FormulaOneTeamDto> getTeams() {
        return formulaOneTeamService.getAllTeams();
    }

    @GetMapping("/formulaoneteam/{id}")
    public ResponseEntity<?> getTeam(@PathVariable Long id) {
        Optional<FormulaOneTeamDto> formulaOneTeamById = formulaOneTeamService.getFormulaOneTeamById(id);
        return formulaOneTeamById.isPresent()
                ? ResponseEntity.ok().body(formulaOneTeamById.get())
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/formulaoneteam")
    public ResponseEntity<FormulaOneTeamDto> createTeam(@Valid @RequestBody FormulaOneTeamDto team) throws URISyntaxException {
        log.info("Request to create: {}", team);
        FormulaOneTeamDto savedFormulaOneTeamDto = formulaOneTeamService.saveFormulaOneTeam(team);
        return ResponseEntity.created(new URI("/api/formulaoneteam/" + savedFormulaOneTeamDto.id()))
                .body(savedFormulaOneTeamDto);
    }

    @PutMapping("/formulaoneteam/{id}")
    public ResponseEntity<FormulaOneTeamDto> updateGroup(@Valid @RequestBody FormulaOneTeamDto team) {
        log.info("Request to update: {}", team);
        FormulaOneTeamDto updatedFormulaOneTeamDto = formulaOneTeamService.saveFormulaOneTeam(team);
        return ResponseEntity.ok().body(updatedFormulaOneTeamDto);
    }

    @DeleteMapping("/formulaoneteam/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable Long id) {
        log.info("Request to delete: {}", id);
        formulaOneTeamService.deleteFormulaOneTeam(id);
        return ResponseEntity.ok().build();
    }
}
