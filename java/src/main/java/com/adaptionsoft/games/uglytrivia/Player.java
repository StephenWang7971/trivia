package com.adaptionsoft.games.uglytrivia;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: wanghongliang
 * Date: 13-12-6
 * Time: 下午3:44
 * To change this template use File | Settings | File Templates.
 */
public class Player {
    private static final int GOLD_COIN_COUNT_TO_WIN = 6;
    private final String name;
    public int purse;
    public int position;
    private boolean inPenaltyBox;

    Random rand = new Random();

    public Player(String name, int pos) {
        this.name = name;
        position = pos;
        purse = 0;
        inPenaltyBox = false;
    }

    //TODO question is not used, but it is for the future.
    public void answer(Question question) {
        if (rand.nextInt(9) == 7) {
            penalty();
        } else {
            award();
        }
    }

    public boolean isWin() {
        return purse == GOLD_COIN_COUNT_TO_WIN;
    }

    public String toString() {
        return name;
    }

    public int rollDice() {
        int roll = rand.nextInt(5) + 1;
        System.out.println(name + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (isInPenaltyBox()) {
            if (roll % 2 != 0) {
                gettingOutOfPenaltyBox();
                return roll;
            } else {
                System.out.println(name + " is not getting out of the penalty box");
                return 0;
            }
        } else {
            return roll;
        }
    }

    public void complyRoll(int roll) {
        position = (position + roll) % 12;

        System.out.println(name  + "'s new location is "  + position);
        System.out.println("The category is " + currentCategory());
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void penalty() {
        System.out.println("Question was incorrectly answered");
        System.out.println(name + " was sent to the penalty box");
        inPenaltyBox = true;
    }

    public void gettingOutOfPenaltyBox() {
        System.out.println(name + " is getting out of the penalty box");
        inPenaltyBox = false;
    }

    public void award() {
        System.out.println("Answer was correct!!!!");
        purse++;
        System.out.println(name + " now has " + purse  + " Gold Coins.");
    }

    public Question.Category currentCategory() {
        return Question.Category.find(position % 4);
    }
}
