/*
 * Copyright (c) 2023. Created by Andras Laczo.
 */

package hu.silveroctopus.forma1.services;

import hu.silveroctopus.forma1.dto.FormulaOneTeamDTO;
import hu.silveroctopus.forma1.model.FormulaOneTeam;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FormulaOneTeamService {

    List<FormulaOneTeam> findAll();

    FormulaOneTeam findById(Integer id);
    FormulaOneTeam create(FormulaOneTeamDTO formulaOneTeamDTO);
    FormulaOneTeam update(FormulaOneTeamDTO formulaOneTeamDTO);
    void delete(FormulaOneTeamDTO formulaOneTeamDTO);



}
