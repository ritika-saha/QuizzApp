package com.sys;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Leaderboard {
    private List<Participant> topParticipants;

    public Leaderboard() {
        this.topParticipants = new ArrayList<>();
    }

    public void updateLeaderboard(Participant participant) {
        // Update the leaderboard after each quiz completion
        if (!topParticipants.contains(participant)) {
            topParticipants.add(participant);
            topParticipants.sort(Comparator.comparingInt(Participant::getScore).reversed());
        }
    }

    public void displayLeaderboard() {
        // Display the top participants along with their scores
        System.out.println("Leaderboard:");
        for (int i = 0; i < topParticipants.size(); i++) {
            Participant participant = topParticipants.get(i);
            System.out.println((i + 1) + ". " + participant.getName() + " - Score: " + participant.getScore());
        }
    }
}
