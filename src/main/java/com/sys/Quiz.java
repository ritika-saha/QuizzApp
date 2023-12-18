package com.sys;

import java.util.ArrayList;
import java.util.List;

// Quiz class represents a quiz with a list of questions
public class Quiz {
    public List<Question> questions;
    

    public Quiz(int id) {
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
