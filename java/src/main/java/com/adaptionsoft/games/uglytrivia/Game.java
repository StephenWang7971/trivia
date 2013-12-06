package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    ArrayList<Player> players = new ArrayList<Player>();
//    boolean[] inPenaltyBox = new boolean[6];

    LinkedList<Question> popQuestions = new LinkedList<Question>();
    LinkedList<Question> scienceQuestions = new LinkedList<Question>();
    LinkedList<Question> sportsQuestions = new LinkedList<Question>();
    LinkedList<Question> rockQuestions = new LinkedList<Question>();

    LinkedList[] questions = new LinkedList[4];

    int currentPlayer = 0;
//    boolean isGettingOutOfPenaltyBox;

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
        return (howManyPlayers() >= 2);
    }

    public boolean add(String playerName) {
        players.add(new Player(playerName, 0));
        //inPenaltyBox[howManyPlayers()] = false;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        System.out.println(currentPlayer() + " is the current player");
        System.out.println("They have rolled a " + roll);

//        if (inPenaltyBox[currentPlayer]) {
        if (currentPlayer().isInPenaltyBox()) {
            if (roll % 2 != 0) {
                currentPlayer().gettingOutOfPenaltyBox();
//                isGettingOutOfPenaltyBox = true;
                System.out.println(currentPlayer() + " is getting out of the penalty box");
                round(roll);
            } else {
                System.out.println(currentPlayer() + " is not getting out of the penalty box");
//                isGettingOutOfPenaltyBox = false;
            }
        } else {
            round(roll);
        }
    }

    private void round(int roll) {
        currentPlayer().rollDice(roll);

        System.out.println(currentPlayer()
                + "'s new location is "
                + currentPlayer().position);
        System.out.println("The category is " + currentCategory());
        askQuestion();
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

    public boolean wasCorrectlyAnswered() {
//        if (inPenaltyBox[currentPlayer]) {
        if (currentPlayer().isInPenaltyBox()) {
//            if (isGettingOutOfPenaltyBox) {
//                return award();
//            } else {
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;
                return true;
//            }
        } else {
            return award();
        }
    }

    private boolean award() {
        System.out.println("Answer was correct!!!!");
        currentPlayer().purse++;
        System.out.println(currentPlayer()
                + " now has "
                + currentPlayer().purse
                + " Gold Coins.");

        boolean winner = currentPlayer().isWin();
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;

        return winner;
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayer() + " was sent to the penalty box");
        currentPlayer().goInPenaltyBox();

        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
        return true;
    }

}
