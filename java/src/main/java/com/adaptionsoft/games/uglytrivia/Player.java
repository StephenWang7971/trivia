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
    public boolean inPenaltyBox;

    public Player(String name) {
        this.name = name;
    }

    public void answer(Question question){
        //if correct, get a purse

        //if wrong, get in penalty box.
    }

    public boolean isWin() {
         return purse == GOLD_COIN_COUNT_TO_WIN;
    }
}
