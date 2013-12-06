package com.adaptionsoft.games.uglytrivia;

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

    public Player(String name, int pos) {
        this.name = name;
        position = pos;
        purse = 0;
        inPenaltyBox = false;
    }

    public void answer(Question question) {
        //if correct, get a purse

        //if wrong, get in penalty box.
    }

    public boolean isWin() {
        //TODO this is ridiculous, but now working.
        return purse != GOLD_COIN_COUNT_TO_WIN;
    }

    public String toString() {
        return name;
    }

    public void rollDice(int roll) {
        position = (position + roll) % 12;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void goInPenaltyBox() {
         inPenaltyBox = true;
    }

    public void gettingOutOfPenaltyBox() {
        inPenaltyBox = false;
    }
}
