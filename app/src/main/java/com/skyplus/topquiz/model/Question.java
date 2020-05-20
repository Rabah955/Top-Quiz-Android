package com.skyplus.topquiz.model;

import java.util.List;

public class Question {

    private String Question;
    private List<String> ChoiceList;
    private int AnswerIndex;

    public Question( String question, List<String> ChoiceList, int AnswerIndex){
        this.setQuestion(question);
        this.setChoiceList(ChoiceList);
        this.setAnswerIndex(AnswerIndex);
    }

    public String getQuestion() {
        return this.Question;
    }

    private void setQuestion(String question) {
        Question = question;
    }

    public List<String> getChoiceList() {
        return ChoiceList;
    }

    private void setChoiceList(List<String> choiceList) {
        if (choiceList == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }

        ChoiceList = choiceList;
    }

    public int getAnswerIndex() {
        return AnswerIndex;
    }

    private void setAnswerIndex(int answerIndex) {
        if (answerIndex < 0 || answerIndex >= ChoiceList.size()) {
            throw new IllegalArgumentException("Answer index is out of bound");
        }

        AnswerIndex = answerIndex;
    }

    @Override
    public String toString() {
        return "Question{" +
                "Question='" + Question + '\'' +
                ", ChoiceList=" + ChoiceList +
                ", AnswerIndex=" + AnswerIndex +
                '}';
    }
}

