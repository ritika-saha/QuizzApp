import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.sys.IncompleteQuizException;
import com.sys.Participant;
import com.sys.Question;
import com.sys.Quiz;
import com.sys.QuizManager;

public class AppTest {
    @Test
    public void testQuizCreation(){
        
        Question question1=new Question("Is plant green ? a:yes, b:no", List.of("1:a","2:b"), 1,"sdfsf");
        Question question2=new Question("Is sky blue ? a:yes, b:no", List.of("1:a","2:b"), 1,"sdfsdfs");
        Question question3=new Question("Is soil brown ? a:yes, b:no", List.of("1:a","2:b"), 1,"sdfsdf");

        Quiz quiz=new Quiz(1);
        quiz.addQuestion(question3);
        quiz.addQuestion(question1);
        quiz.addQuestion(question2);
        int quizSizeExpected=3;
        int quizSizeCalculated=quiz.questions.size();
        assertEquals(quizSizeCalculated, quizSizeExpected);
    }

    @Test 
    public void testParticipant(){
             Question question1=new Question("Is plant green ? a:yes, b:no", List.of("1:a","2:b"), 1,"sdfsf");
        Question question2=new Question("Is sky blue ? a:yes, b:no", List.of("1:a","2:b"), 1,"sdfsdfs");
        Question question3=new Question("Is soil brown ? a:yes, b:no", List.of("1:a","2:b"), 1,"sdfsdf");

        Quiz quiz=new Quiz(1);
        quiz.addQuestion(question3);
        quiz.addQuestion(question1);
        quiz.addQuestion(question2);
        Participant participant=new Participant("john");
        String expectedName="john";
        String storedName=participant.getName();
        assertEquals(expectedName, storedName);
    }

    @Test
    public void testScore(){

             Question question1=new Question("Is plant green ? a:yes, b:no", List.of("1:a","2:b"), 1,"sdfsf");
        Question question2=new Question("Is sky blue ? a:yes, b:no", List.of("1:a","2:b"), 1,"sdfsdfs");
        Question question3=new Question("Is soil brown ? a:yes, b:no", List.of("1:a","2:b"), 1,"sdfsdf");

        Quiz quiz=new Quiz(1);
        quiz.addQuestion(question3);
        quiz.addQuestion(question1);
        quiz.addQuestion(question2);
        Participant participant=new Participant("john");
        participant.answerQuestion(question1, 1);
        participant.answerQuestion(question2, 2);
        participant.answerQuestion(question3, 1);
        QuizManager manager=new QuizManager();
        manager.quiz=quiz;
        int expectedScore=2;
        int calculatedScore=manager.scoreQuiz(participant);
        assertEquals(expectedScore, calculatedScore);
    }

    @Test
    public void testIncompleteQuiz(){

            Question question1=new Question("Is plant green ? a:yes, b:no", List.of("1:a","2:b"), 1,"sdfsf");
        Question question2=new Question("Is sky blue ? a:yes, b:no", List.of("1:a","2:b"), 1,"sdfsdfs");
        Question question3=new Question("Is soil brown ? a:yes, b:no", List.of("1:a","2:b"), 1,"sdfsdf");

        Quiz quiz=new Quiz(1);
        quiz.addQuestion(question3);
        quiz.addQuestion(question1);
        quiz.addQuestion(question2);
        Participant participant=new Participant("john");
        participant.answerQuestion(question1, 1);
        participant.answerQuestion(question2, 2);
        QuizManager manager=new QuizManager();
        manager.quizList.add(quiz);
        IncompleteQuizException e=assertThrows(IncompleteQuizException.class, () -> manager.quizTaker(participant, 0));
        assertEquals(e.getMessage(), "Quiz submission incomplete. Please answer all questions.");
    }

    @Test
    public void testMultipleQuiz(){

            Question question1=new Question("Is plant green ? a:yes, b:no", List.of("1:a","2:b"), 1,"sdfsf");
        Question question2=new Question("Is sky blue ? a:yes, b:no", List.of("1:a","2:b"), 1,"sdfsdfs");
        Question question3=new Question("Is soil brown ? a:yes, b:no", List.of("1:a","2:b"), 1,"sdfsdf");

        Quiz quiz=new Quiz(1);
        quiz.addQuestion(question3);
        quiz.addQuestion(question1);
        quiz.addQuestion(question2);
        QuizManager manager=new QuizManager();
        manager.quizList.add(quiz);

            Question question4=new Question("Is plant green ? a:yes, b:no", List.of("1:a","2:b"), 1,"sdfsf");
        Question question5=new Question("Is sky blue ? a:yes, b:no", List.of("1:a","2:b"), 1,"sdfsdfs");
        Question question6=new Question("Is soil brown ? a:yes, b:no", List.of("1:a","2:b"), 1,"sdfsdf");

        Quiz quiz2=new Quiz(2);
        quiz2.addQuestion(question4);
        quiz2.addQuestion(question5);
        quiz2.addQuestion(question6);
        manager.quizList.add(quiz2);

        int expectedQuizNo=2;
        int calculatedQuizNo=manager.quizList.size();
        assertEquals(expectedQuizNo, calculatedQuizNo);
    }

    


}
