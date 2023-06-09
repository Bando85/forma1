/*
 * Copyright (c) 2023. Created by Andras Laczo.
 */

package hu.silveroctopus.forma1.repository;

import hu.silveroctopus.forma1.model.FormulaOneTeam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormulaOneTeamRepository extends JpaRepository<FormulaOneTeam, Long> {
}
