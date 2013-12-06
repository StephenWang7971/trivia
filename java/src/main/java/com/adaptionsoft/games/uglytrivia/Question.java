package com.adaptionsoft.games.uglytrivia;

/**
 * Created with IntelliJ IDEA.
 * User: wanghongliang
 * Date: 13-12-6
 * Time: 下午3:44
 * To change this template use File | Settings | File Templates.
 */
public class Question {
    private final Category category;
    private final String title;

    public Question(String title, Category category) {
        this.title = title;
        this.category = category;
    }

    public static enum Category {
        POP,
        SCIENCE,
        SPORTS,
        ROCK;
    }
}
