package com.sys;

// Custom exception for incorrect answers
public class IncorrectAnswerException extends Exception {
    public IncorrectAnswerException(String message) {
        super(message);
    }
}
