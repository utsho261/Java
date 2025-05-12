package utsho;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FootballTeamManager {

    private List<String> players;

    public FootballTeamManager() {
        players = new ArrayList<>();
    }

    public void readPlayers() {
        if (players.isEmpty()) {
            System.out.println("No players in the team.");
        } else {
            System.out.println("Players in the team:");
            for (int i = 0; i < players.size(); i++) {
                System.out.println((i + 1) + ". " + players.get(i));
            }
        }
    }

    public void addPlayer(String playerName) {
        players.add(playerName);
        System.out.println(playerName + " has been added to the team.");
    }

    public void updatePlayer(int index, String newName) {
        if (index >= 0 && index < players.size()) {
            players.set(index, newName);
            System.out.println("Player at position " + (index + 1) + " has been updated.");
        } else {
            System.out.println("Invalid index.");
        }
    }

    public void deletePlayer(int index) {
        if (index >= 0 && index < players.size()) {
            String removedPlayer = players.remove(index);
            System.out.println(removedPlayer + " has been removed from the team.");
        } else {
            System.out.println("Invalid index.");
        }
    }


}
