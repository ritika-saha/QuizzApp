package com.sys;

import java.util.List;

public class Question {
    public String questionText;
    private List<String> options;
    public int correctOption;
    public String correctOptionExplaination;
    public String category;

    public Question(String questionText, List<String> options, int correctOption,String CorrectOptionExplaination,String category) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
        this.correctOptionExplaination= CorrectOptionExplaination;
        this.category=category;
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

    public String getCategory(){
        return category;
    }
}
