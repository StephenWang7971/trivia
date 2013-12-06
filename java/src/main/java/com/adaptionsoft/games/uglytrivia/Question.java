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
        POP(0),
        SCIENCE(1),
        SPORTS(2),
        ROCK(3);

        private int value;

        Category(int value) {
            this.value = value;
        }

        public static Category find(int index) {
            switch (index) {
                case 0:
                    return POP;
                case 1:
                    return SCIENCE;
                case 2:
                    return SPORTS;
                case 3:
                    return ROCK;
            }
            return null;
        }

        public int value() {
            return value;
        }
    }
}
