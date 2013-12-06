package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wanghongliang
 * Date: 13-12-6
 * Time: 下午5:07
 * To change this template use File | Settings | File Templates.
 */
public class Host {
    //TODO consider write as Host.ask(player).from(questions);
    public static Question askQuestionTo(Player player,Map<Question.Category, LinkedList<Question>> questions) {
        Question.Category category = Question.Category.find(player.position % 4);
        LinkedList<Question> queue = questions.get(category);
        Question q =  queue.get(0);
        queue.removeFirst();
        return q;
    }
}
