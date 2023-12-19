package com.sys;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// QuizManager class handles quiz-related operations
public class QuizManager {
    public Quiz quiz;
    public ArrayList<Quiz> quizList;
    private Leaderboard leaderboard;

    public QuizManager() {
        //this.quiz = new Quiz();
        this.quizList=new ArrayList<>();
        this.leaderboard = new Leaderboard();
    }

    public void createQuiz(){
        List<String> options = List.of("Option A", "Option B", "Option C", "Option D");
        Question question = new Question("What is the capital of France?", options, 1,"because it is");
        quiz.addQuestion(question);
        quizList.add(quiz);
    }

    public void createQuizwithOption(Integer id) {
        Quiz quiz=new Quiz(id);
        List<String> options = List.of("Option A", "Option B", "Option C", "Option D");
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter 1 to add question, 2 to delete question , 3 to edit question, 0 to exit");
        String choice=sc.nextLine();
        while(choice.equals("0")==false){
            switch (choice) {
                case "1":
                        System.out.println("Enter Question with options");
                        String q=sc.nextLine();
                        System.out.println("Enter number for correct option form 1 to 4");
                        int correct=sc.nextInt();
                        sc.nextLine();
                        System.out.println("Enter explanation for correct option");
                        String explain=sc.nextLine();
                        Question question = new Question(q, options, correct,explain);    
                        quiz.addQuestion(question);  
                        System.out.println("Enter 1 to add question, 2 to delete question , 3 to edit question, 0 to exit");
                        choice=sc.nextLine();           
                    break;

                case "2":
                    List<Question> questionList=quiz.getQuestions();
                    for(Question ques:questionList){
                        System.out.println("Do you want to remove the following? (yes/no)\n"+ ques.getQuestionText());
                        String c=sc.nextLine();
                        if(c.equals("yes")){
                            quiz.getQuestions().remove(ques);
                            System.out.println("question removed");
                            break;
                        }
                    }
                    System.out.println("Enter 1 to add question, 2 to delete question , 3 to edit question, 0 to exit");
                    choice=sc.nextLine();  
                    break;

                case "3":
                List<Question> questionList1=quiz.getQuestions();
                    for(Question ques:questionList1){
                        System.out.println("Do you want to edit the following? (yes/no)\n"+ ques.getQuestionText());
                        String c=sc.nextLine();
                        if(c.equals("yes")){
                            System.out.println("Enter new question statement");
                            String s=sc.nextLine();
                            ques.questionText=s;
                            System.out.println("Enter new correct option");
                            int opt=sc.nextInt();
                            sc.nextLine();
                            ques.correctOption=opt;
                            System.out.println("Enter new explaination");
                            String exp=sc.nextLine();
                            ques.correctOptionExplaination=exp;
                            System.out.println("question edited");
                            quiz.questions=questionList1;
                            break;
                        }
                    }
                    System.out.println("Enter 1 to add question, 2 to delete question , 3 to edit question, 0 to exit");
                    choice=sc.nextLine();  
                    break;

                case "0":break;
            
                default:
                    break;
            }
        }

        quizList.add(quiz);





    }

  

    public void takeQuiz(Participant participant, int ind) throws IncompleteQuizException {
        String option;
        Quiz quizz=quizList.get(ind);
        this.quiz=quizz;
        Scanner sc=new Scanner(System.in);
        System.out.println("enter 0 to submit quiz or enter 1 to continue");
        option=sc.nextLine();
        for (Question question : quiz.getQuestions()) {
            if(option.equals("1")){
            displayQuestion(question);
            int selectedOption = getUserInput();
            participant.answerQuestion(question, selectedOption);
            System.out.println("enter 0 to submit quiz or enter 1 to continue");
            option=sc.nextLine();
            // Update leaderboard after each question
            leaderboard.updateLeaderboard(participant);
            }
        }

        if (!isQuizComplete(participant)) {
            throw new IncompleteQuizException("Quiz submission incomplete. Please answer all questions.");
        }
        if(!isQuizComplete(participant)){
            for (Question question : quiz.getQuestions()) {
                if(participant.answers.containsKey(question)==false){
                    displayQuestion(question);
                    int selectedOption = getUserInput();
                    participant.answerQuestion(question, selectedOption);
                }
            }
        }

       
        
    }

    private boolean isQuizComplete(Participant participant) {
        return participant.getAnswers().size() == quiz.getQuestions().size();
    }

    private int getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Your choice: ");
        return scanner.nextInt();
    }

    private void displayQuestion(Question question) {
        System.out.println(question.getQuestionText());
        List<String> options = question.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    public int scoreQuiz(Participant participant)  {
        int score = 0;
        for (Map.Entry<Question, Integer> entry : participant.getAnswers().entrySet()) {
            Question question = entry.getKey();
            int selectedOption = entry.getValue();

            if (selectedOption == question.getCorrectOption()) {
                score++;
            } else {
                
                System.out.println("Incorrect answer for question: " + question.getQuestionText());
                System.out.println("Correct option : "+ question.getCorrectOption());
                System.out.println("Explanation : "+question.getCorrectOptionExplaination());
            }
        }

        System.out.println("----------------------------FEEDBACK-------------------------");
        System.out.println("Participant: " + participant.getName());
        System.out.println("Score: " + score + "/" + quiz.getQuestions().size());
        participant.setScore(score);
        return score;
    }


      public void quizTaker(Participant participant,int ind) throws IncompleteQuizException{
         Quiz quizz=quizList.get(ind);
        this.quiz=quizz;
         if (!isQuizComplete(participant)) {
            throw new IncompleteQuizException("Quiz submission incomplete. Please answer all questions.");
        }
        if(!isQuizComplete(participant)){
            for (Question question : quiz.getQuestions()) {
                if(participant.answers.containsKey(question)==false){
                    displayQuestion(question);
                    int selectedOption = getUserInput();
                    participant.answerQuestion(question, selectedOption);
                }
            }
        }
    }

    public void displayLeaderboard() {
        leaderboard.displayLeaderboard();
    }
}

