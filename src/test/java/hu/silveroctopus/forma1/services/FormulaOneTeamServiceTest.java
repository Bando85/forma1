package hu.silveroctopus.forma1.services;

import hu.silveroctopus.forma1.dto.FormulaOneTeamDto;
import hu.silveroctopus.forma1.model.FormulaOneTeam;
import hu.silveroctopus.forma1.repository.FormulaOneTeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.Optional;

@SpringBootTest
class FormulaOneTeamServiceTest {

    @MockBean
    FormulaOneTeamRepository formulaOneTeamRepository;

    @Autowired
    FormulaOneTeamService formulaOneTeamService;

    @Test
    void testGetAllTeams() {

        when(formulaOneTeamRepository.findAll()) .thenReturn(List.of(
                FormulaOneTeam.builder()
                        .id(1L)
                        .name("test")
                        .entryFeePaid(true)
                        .foundationYear(1950)
                        .worldChampionshipsWon(1)
                        .build(),
                FormulaOneTeam.builder()
                        .id(2L)
                        .name("test2")
                        .entryFeePaid(true)
                        .foundationYear(1950)
                        .worldChampionshipsWon(1)
                        .build()
        )) ;

        List<FormulaOneTeamDto> result = formulaOneTeamService.getAllTeams();
        assertEquals(2, result.size());
    }

    @Test
    void testGetFormulaOneTeamById() {

        when(formulaOneTeamRepository.findById(2L)).thenReturn(Optional.of(
                FormulaOneTeam.builder()
                        .id(2L)
                        .name("test2")
                        .entryFeePaid(true)
                        .foundationYear(1950)
                        .worldChampionshipsWon(1)
                        .build()
        )) ;

        Optional<FormulaOneTeamDto> formulaOneTeamDtoOptional = formulaOneTeamService.getFormulaOneTeamById(2L);
        assertEquals("test2", formulaOneTeamDtoOptional.get().name());
        assertEquals(true, formulaOneTeamDtoOptional.get().entryFeePaid());
        assertEquals(1950, formulaOneTeamDtoOptional.get().foundationYear());
        assertEquals(1, formulaOneTeamDtoOptional.get().worldChampionshipsWon());
    }
}