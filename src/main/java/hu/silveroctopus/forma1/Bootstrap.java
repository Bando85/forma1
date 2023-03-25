/*
 * Copyright (c) 2023. Created by Andras Laczo.
 */

package hu.silveroctopus.forma1;

import hu.silveroctopus.forma1.model.FormulaOneTeam;
import hu.silveroctopus.forma1.model.User;
import hu.silveroctopus.forma1.repository.FormulaOneTeamRepository;
import hu.silveroctopus.forma1.repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final FormulaOneTeamRepository formulaOneTeamRepository;
    private final UserRepository userRepository;

    public Bootstrap(FormulaOneTeamRepository formulaOneTeamRepository, UserRepository userRepository) {
        this.formulaOneTeamRepository = formulaOneTeamRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadData();
    }


    private void loadData(){

        ArrayList<FormulaOneTeam> list = new ArrayList<>();

        list.add(FormulaOneTeam.builder()
                .name("Mercedes")
                .foundationYear(1950)
                .worldChampionshipsWon(5)
                .entryFeePaid(true)
                .build());
        list.add(FormulaOneTeam.builder()
                .name("Renault")
                .foundationYear(1960)
                .worldChampionshipsWon(6)
                .entryFeePaid(true)
                .build());
        list.add(FormulaOneTeam.builder()
                .name("Jaguar")
                .foundationYear(1940)
                .worldChampionshipsWon(1)
                .entryFeePaid(false)
                .build());

        formulaOneTeamRepository.saveAll(list);

        User admin = User.builder()
                .userName("admin")
                .authority("ADMIN")
                .password("$2a$05$unLz42AKXIypNPOEXHQJKuMW3ODkm/pSNz2tKIXEDb0BFrSapHiMe")
                .build();

        userRepository.save(admin);


    }
}
