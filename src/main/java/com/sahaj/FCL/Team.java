package com.sahaj.FCL;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@Slf4j
public class Team {
    private String name;
    private List<Player> players;

    public Team(List<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player) {
        if (this.players.size() == 6) {
            log.warn("Teams is already full, can not and any more players");
            return;
        }
        this.players.add(player);
    }
}
