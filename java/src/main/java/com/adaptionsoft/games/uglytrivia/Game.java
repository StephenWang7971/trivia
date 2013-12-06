package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Game {
    ArrayList<Player> players = new ArrayList<Player>();

    LinkedList<Question> popQuestions = new LinkedList<Question>();
    LinkedList<Question> scienceQuestions = new LinkedList<Question>();
    LinkedList<Question> sportsQuestions = new LinkedList<Question>();
    LinkedList<Question> rockQuestions = new LinkedList<Question>();

    LinkedList[] questions = new LinkedList[4];
    Random rand = new Random();

    int currentPlayer = 0;

    public Game() {
        questions[0] = popQuestions;
        questions[1] = scienceQuestions;
        questions[2] = sportsQuestions;
        questions[3] = rockQuestions;
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast(new Question("Pop Question " + i, Question.Category.POP));
            scienceQuestions.addLast(new Question("Science Question " + i, Question.Category.SCIENCE));
            sportsQuestions.addLast(new Question("Sports Question " + i, Question.Category.SPORTS));
            rockQuestions.addLast(createRockQuestion(i));
        }
    }

    public Question createRockQuestion(int index) {
        return new Question("Rock Question " + index, Question.Category.ROCK);
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
        System.out.println("The category is " + currentCategory());
        askQuestion();

        if (rand.nextInt(9) == 7) {
            penalty();
        } else {
            award();
        }
    }

    private Player currentPlayer() {
        return players.get(currentPlayer);
    }

    private void askQuestion() {
        questions[currentCategory().value()].removeFirst();
    }

    private Question.Category currentCategory() {
        return Question.Category.find(currentPlayer().position % 4);
    }

    public boolean nextPlayer() {
        boolean winner = currentPlayer().isWin();
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
        return winner;
    }

    private void award() {
        currentPlayer().award();
    }

    public void penalty() {
        currentPlayer().goInPenaltyBox();
    }

}
