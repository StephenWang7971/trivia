package com.adaptionsoft.games.uglytrivia;

import java.util.*;

public class Game {
    ArrayList<Player> players = new ArrayList<Player>();

    Map<Question.Category, LinkedList> questions = new HashMap<Question.Category, LinkedList>();
    Random rand = new Random();

    int currentPlayer = 0;

    public Game() {
        LinkedList<Question> popQuestions = new LinkedList<Question>();
        LinkedList<Question> scienceQuestions = new LinkedList<Question>();
        LinkedList<Question> sportsQuestions = new LinkedList<Question>();
        LinkedList<Question> rockQuestions = new LinkedList<Question>();
        questions.put(Question.Category.POP, popQuestions);
        questions.put(Question.Category.SCIENCE, scienceQuestions);
        questions.put(Question.Category.SPORTS, sportsQuestions);
        questions.put(Question.Category.ROCK, rockQuestions);
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast(new Question("Pop Question " + i, Question.Category.POP));
            scienceQuestions.addLast(new Question("Science Question " + i, Question.Category.SCIENCE));
            sportsQuestions.addLast(new Question("Sports Question " + i, Question.Category.SPORTS));
            rockQuestions.addLast(new Question("Rock Question " + i, Question.Category.ROCK));
        }
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
        int roll = rand.nextInt(5) + 1;
        System.out.println(currentPlayer() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (currentPlayer().isInPenaltyBox()) {
            if (roll % 2 != 0) {
                currentPlayer().gettingOutOfPenaltyBox();
                round(roll);
            } else {
                System.out.println(currentPlayer() + " is not getting out of the penalty box");
            }
        } else {
            round(roll);
        }
    }

    private void round(int roll) {
        currentPlayer().rollDice(roll);
        askQuestion();
        currentPlayer().answer();
    }

    private Player currentPlayer() {
        return players.get(currentPlayer);
    }

    private void askQuestion() {
        questions.get(currentPlayer().currentCategory()).removeFirst();
    }

    public boolean nextPlayer() {
        boolean winner = currentPlayer().isWin();
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
        return winner;
    }

}
