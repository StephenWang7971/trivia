package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Game {
    ArrayList<Player> players = new ArrayList<Player>();

    int currentPlayer = 0;

    public Game() {
        QuestionSet.getInstance().init();
    }

    public boolean isPlayable() {
        return players.size() >= 2;
    }

    public boolean add(String playerName) {
        players.add(new Player(playerName, 0));

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }

    public void roll() {
        int roll = currentPlayer().rollDice();
        if (roll > 0) {
            round(roll);
        }
    }

    private void round(int roll) {
        currentPlayer().complyRoll(roll);
        Question question = Host.askQuestionTo(currentPlayer());
        currentPlayer().answer(question);
    }

    private Player currentPlayer() {
        return players.get(currentPlayer);
    }

    public boolean nextPlayer() {
        boolean winner = currentPlayer().isWin();
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
        return winner;
    }

}
