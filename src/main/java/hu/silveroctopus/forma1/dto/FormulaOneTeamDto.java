package hu.silveroctopus.forma1.dto;

import lombok.Builder;

@Builder
public record FormulaOneTeamDto(

        Long id,

        String name,

        Integer foundationYear,

        Integer worldChampionshipsWon,

        boolean entryFeePaid) {};
