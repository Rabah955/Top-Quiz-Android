package com.skyplus.topquiz.model;


import java.util.Collections;
import java.util.List;

public class QuestionBank {
    private List<Question> QuestionList;
    private int NextQuestionIndex;

    public QuestionBank(List<Question> QuestionList){
        this.QuestionList = QuestionList;

        // Shuffle the question list
        Collections.shuffle(QuestionList);

        NextQuestionIndex = 0;
    }

    public Question getQuestion() {
        // Ensure we loop over the questions
        if (NextQuestionIndex == QuestionList.size()) {
            NextQuestionIndex = 0;
        }

        // Please note the post-incrementation
        return QuestionList.get(NextQuestionIndex++);
    }

}
