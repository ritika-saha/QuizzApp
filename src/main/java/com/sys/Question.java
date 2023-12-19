package com.sys;

import java.util.List;

public class Question {
    public String questionText;
    private List<String> options;
    public int correctOption;
    public String correctOptionExplaination;

    public Question(String questionText, List<String> options, int correctOption,String CorrectOptionExplaination) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
        this.correctOptionExplaination= CorrectOptionExplaination;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }

    public String getCorrectOptionExplaination(){
        return correctOptionExplaination;
    }
}
