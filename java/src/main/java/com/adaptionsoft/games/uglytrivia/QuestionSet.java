package com.adaptionsoft.games.uglytrivia;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wanghongliang
 * Date: 13-12-6
 * Time: 下午5:13
 * To change this template use File | Settings | File Templates.
 */
public class QuestionSet {

    private static QuestionSet instance = null;

    private Map<Question.Category, LinkedList<Question>> questions = new HashMap<Question.Category, LinkedList<Question>>();

    public void init() {
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

    public static QuestionSet getInstance() {
        if (instance == null) {
            instance = new QuestionSet();
        }
        return instance;
    }

    public Map<Question.Category, LinkedList<Question>> getQuestions() {
        return questions;
    }
}
