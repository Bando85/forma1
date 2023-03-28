/*
 * Copyright (c) 2023. Created by Andras Laczo.
 */

package hu.silveroctopus.forma1.web;

import hu.silveroctopus.forma1.model.FormulaOneTeam;
import hu.silveroctopus.forma1.repository.FormulaOneTeamRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class FormulaOneTeamController {

    private FormulaOneTeamRepository repository;

    private final Logger log = LoggerFactory.getLogger(FormulaOneTeamController.class);

    public FormulaOneTeamController(FormulaOneTeamRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/formulaoneteams")
    Collection<FormulaOneTeam> getTeams() {
        return repository.findAll();
    }

    @GetMapping("/formulaoneteam/{id}")
    ResponseEntity<?> getTeam(@PathVariable Long id) {
        Optional<FormulaOneTeam> team = repository.findById(id);
        return team.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/formulaoneteam")
    ResponseEntity<FormulaOneTeam> createTeam(@Valid @RequestBody FormulaOneTeam team) throws URISyntaxException {
        log.info("Request to create: {}", team);
        FormulaOneTeam result = repository.save(team);
        return ResponseEntity.created(new URI("/api/formulaoneteam/" + result.getId()))
                .body(result);
    }

    @PutMapping("/formulaoneteam/{id}")
    ResponseEntity<FormulaOneTeam> updateGroup(@Valid @RequestBody FormulaOneTeam team) {
        log.info("Request to update: {}", team);
        FormulaOneTeam result = repository.save(team);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/formulaoneteam/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable Long id) {
        log.info("Request to delete: {}", id);
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
