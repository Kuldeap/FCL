package com.sahaj.FCL;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
@Slf4j
public class WinnerCheckService {


    public String readAllLines(String path) {
        List<String> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                String ln = scanner.nextLine();
                records.add(ln);
                System.out.println(ln);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        var team1 = createTeamObject(records.subList(0, 7));
        var team2 = createTeamObject(records.subList(7, 14));
        int team1Score = calculateTeamScore(team1);
        int team2Score = calculateTeamScore(team2);

        log.info("Team 1 score :: " + team1Score);
        log.info("Team 2 score :: " + team2Score);
        return "Winning team is :: " + (team1Score > team2Score ? team1.getName() : team2.getName());
    }

    private Team createTeamObject(List<String> teamAndItsPlayerDetails) {
        var team = new Team(new ArrayList<>());
        team.setName(teamAndItsPlayerDetails.get(0));
        teamAndItsPlayerDetails.stream().skip(1).forEach(str -> {
            String[] split = str.split(",");
            team.addPlayer(Player.builder()
                    .name(split[0])
                    .runs(Integer.parseInt(split[1]))
                    .wickets(Integer.parseInt(split[2]))
                    .build());

        });
        return team;
    }

    private int calculateTeamScore(Team team) {
        return team.getPlayers().stream()
                .map(player -> getScoreForRuns(player.getRuns()) + getScoreForWickets(player.getWickets()))
                .reduce(0, Integer::sum);
    }

    private int getScoreForRuns(Integer runs) {
        if (runs == null)
            return 0;
        if (runs < 1)
            return -5;
        int bonus = 10 * (runs / 25);
        return runs + bonus;
    }

    private int getScoreForWickets(Integer wickets) {
        if (wickets == null)
            return 0;
        int bonus = 20 * (wickets / 3);
        return (wickets * 40) + bonus;
    }

}
