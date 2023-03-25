/*
 * Copyright (c) 2023. Created by Andras Laczo.
 */

package hu.silveroctopus.forma1.dto;

public record FormulaOneTeamDTO(
        String id,
        String name,
        String foundationYear,
        String worldChampionshipsWon,
        String entryFeePaid
) {
}
