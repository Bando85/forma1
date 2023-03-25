/*
 * Copyright (c) 2023. Created by Andras Laczo.
 */

package hu.silveroctopus.forma1.services;

import hu.silveroctopus.forma1.dto.FormulaOneTeamDTO;
import hu.silveroctopus.forma1.model.FormulaOneTeam;
import hu.silveroctopus.forma1.repository.FormulaOneTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormulaOneTeamServiceImpl implements FormulaOneTeamService{

    private FormulaOneTeamRepository repository;


    public FormulaOneTeamServiceImpl(FormulaOneTeamRepository repository) {
        this.repository=repository;
    }


    @Override
    public List<FormulaOneTeam> findAll() {
        return repository.findAll();
    }

    @Override
    public FormulaOneTeam findById(Integer id) {
        Optional<FormulaOneTeam> formulaOneTeam = repository.findById(id);
        return formulaOneTeam
                .orElse(null);

    }

    @Override
    public FormulaOneTeam create(FormulaOneTeamDTO formulaOneTeamDTO) {
        return null;
    }

    @Override
    public FormulaOneTeam update(FormulaOneTeamDTO formulaOneTeamDTO) {
        return null;
    }

    @Override
    public void delete(FormulaOneTeamDTO formulaOneTeamDTO) {

    }
}
