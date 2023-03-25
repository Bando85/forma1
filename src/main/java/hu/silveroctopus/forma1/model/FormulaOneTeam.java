/*
 * Copyright (c) 2023. Created by Andras Laczo.
 */

package hu.silveroctopus.forma1.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teams")
public class FormulaOneTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @NotBlank
    String name;

    Integer foundationYear;

    Integer worldChampionshipsWon;

    boolean entryFeePaid;


}
