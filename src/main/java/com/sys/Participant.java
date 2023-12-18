package com.sys;

import java.util.HashMap;
import java.util.Map;

// Participant class represents a participant taking a quiz
public class Participant {
    private String name;
    public Map<Question, Integer> answers;

    public Participant(String name) {
        this.name = name;
        this.answers = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public Map<Question, Integer> getAnswers() {
        return answers;
    }

    public void answerQuestion(Question question, int selectedOption) {
        answers.put(question, selectedOption);
    }
}