package com.sys;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class Main {

    public Map<String,String> managerParticipantMap=new HashMap<>();
    public Map<String , Integer>questionQuizMap=new HashMap<>();

    public void quizManagerHandler(QuizManager quizManager){
        Scanner sc=new Scanner(System.in);
         System.out.println("Enter 0 to exit quiz creation ");
        String choice=sc.nextLine();
        Integer id=1;
        while (choice.equals("0")==false) {
            System.out.println("----------------making new quiz--------------------");
            quizManager.createQuizwithOption(id);
            id+=1;
            System.out.println("Enter 0 to exit quiz creation ");
            choice=sc.nextLine();
        }
        
       // sc.close();
    }

    public void participantHandler(Participant participant, QuizManager quizManager,String managerName) throws IncorrectAnswerException {
        Scanner sc = new Scanner(System.in);
    
        try {
            String participantName=participant.getName();
            managerParticipantMap.put(participantName,managerName);
            System.out.println("--------------Player: "+participantName+"----------------------");
            int size = quizManager.quizList.size();
            System.out.println("Enter number between 0 to " + (size - 1) + " to choose quiz");
            int ind = sc.nextInt();
            sc.nextLine();  // Consume the newline character
    
            quizManager.takeQuiz(participant, ind);
            int s = quizManager.scoreQuiz(participant);
            quizManager.displayLeaderboard();
        } catch (IncompleteQuizException e) {
            System.out.println("Error: " + e.getMessage());
        } 
    }

    public void writeToCSV(String csvFilePath) {
        String header[] = { "MANAGER_NAME", "PARTICIPANT_NAME" };
        
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath))) {
            writer.writeNext(header);
            for (Map.Entry<String, String> entry : managerParticipantMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                String data[] = {value,key };
                writer.writeNext(data);
            }

            System.out.println("Data Added to CSV File -------------------------------->");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void writeQuestionsToCSV(String csvFilePath) {
        String header[] = { "QUESTIONS", "QUIZ_ID" };
        
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath))) {
            writer.writeNext(header);
            for (Map.Entry<String, Integer> entry : questionQuizMap.entrySet()) {
                String key = entry.getKey();
                Integer i=entry.getValue();
                String value = i.toString();
                String data[] = {key,value };
                writer.writeNext(data);
            }

            System.out.println("Data Added to CSV File -------------------------------->");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void readFromCSV(String filePath){
        
         try(CSVReader reader = new CSVReader(new FileReader(filePath))){
            String[] header = reader.readNext();
            System.out.println(Arrays.toString(header));
            String[]  line;
            while((line = reader.readNext())!=null){
                for(String value: line){
                    System.out.print(value+ " ");
                }
                System.out.println(" ");
            }
        }catch (IOException exception){
            exception.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void main(String[] args) throws IncorrectAnswerException {
        QuizManager quizManager = new QuizManager();
        QuizManager quizManager2=new QuizManager();
        Scanner sc=new Scanner(System.in);
        //quizManager.createQuiz();
       
        Participant participant = new Participant("John Doe");        //manager1
        Participant participant2=new Participant("Ritika Saha");  //manager1
        Participant participant3=new Participant("Jane Smith");      //manager2
        Participant participant4=new Participant("Steve Brown");//manager2
        Participant participant5=new Participant("Kim joon");//manager2
        Main ob=new Main();
        System.out.println("Quiz for manager 1");
        ob.quizManagerHandler(quizManager);
        System.out.println("Quiz for manager 2");
        ob.quizManagerHandler(quizManager2);
        ob.participantHandler(participant, quizManager,"Manager 1");
        ob.participantHandler(participant2, quizManager, "Manager 1");
        ob.participantHandler(participant3, quizManager2,"Manager 2");
        ob.participantHandler(participant4, quizManager2,"Manager 2");
        ob.participantHandler(participant5, quizManager2, "Manager 2");
        System.out.println("Writing and reading manager participant data");
       ob.writeQuestionsToCSV("FileOps/QuizData.csv");
       ob.readFromCSV("FileOps/QuizData.csv");

       ArrayList<Quiz> quizList=quizManager.quizList;
       for(Quiz quiz:quizList){
            List<Question> quesKey=quiz.getQuestions();
            Integer val=quiz.quizID;
            for(Question q:quesKey){
                String key=q.getQuestionText();
                ob.questionQuizMap.put(key, val);
            }
       }

       System.out.println("reading and wrting manager1's quiz and question data");
       ob.writeQuestionsToCSV("FileOps/Manager1QuizData.csv");
       ob.readFromCSV("FileOps/Manager1QuizData.csv");

       System.out.println("reading and writing manager2 quiz and question data");
       quizList=quizManager2.quizList;
       for(Quiz quiz:quizList){
            List<Question> quesKey=quiz.getQuestions();
            Integer val=quiz.quizID;
            for(Question q:quesKey){
                String key=q.getQuestionText();
                ob.questionQuizMap.put(key, val);
            }
       }

        ob.writeQuestionsToCSV("FileOps/Manager2QuizData.csv");
       ob.readFromCSV("FileOps/Manager2QuizData.csv");

       


      //sc.close();
    }
}