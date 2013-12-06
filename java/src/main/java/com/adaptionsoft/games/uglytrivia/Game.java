package com.adaptionsoft.games.uglytrivia;

import java.util.*;

public class Game {
    ArrayList<Player> players = new ArrayList<Player>();

    Map<Question.Category, LinkedList<Question>> questions = new HashMap<Question.Category, LinkedList<Question>>();

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
        int roll = currentPlayer().rollDice();
        if (roll > 0) {
            round(roll);
        }
    }

    private void round(int roll) {
        currentPlayer().complyRoll(roll);
        Question question = Host.askQuestionTo(currentPlayer(), questions);
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
