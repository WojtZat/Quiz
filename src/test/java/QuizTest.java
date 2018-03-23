import QuizInterface.MemoryQuiz;
import QuizInterface.Question;
import QuizInterface.Quiz;
import org.junit.Assert;
import org.junit.Test;

public class QuizTest {
    Quiz quiz = new MemoryQuiz();

    @Test
    public void addTest(){
        quiz.clear();
        quiz.add("Question1");
        quiz.add("Question2");
        quiz.add("Question3");
        quiz.add("Question4");
        quiz.add("Question5");
        quiz.delete(0);
        quiz.delete(1);
        quiz.delete(2);
        quiz.delete(3);
        quiz.delete(4);
    }

    @Test
    public void failAddTest(){
        quiz.clear();
        quiz.add("");
        quiz.delete(0);
    }
    @Test
    public void deleteUnexistingElements(){
        Assert.assertFalse(quiz.delete(-1));
    }
    @Test
    public void deleteUnexistingElements2(){
        Assert.assertFalse(quiz.delete(100));
    }
    @Test //(expected = IndexOutOfBoundsException.class)
    public void failAddTest2(){
        quiz.clear();
        quiz.add("Question1");
        quiz.add("Question1");
        quiz.add("Question1");
        quiz.add("Question1");
        Assert.assertTrue(quiz.delete(0));
        Assert.assertFalse(quiz.delete(0));
    }

    @Test
    public void equalsTest(){
        Question question1 = new Question("question1");
        Question question2 = new Question("question1");
        Question question3 = new Question("question3");
        Assert.assertTrue(question1.equals(question2));
        Assert.assertFalse(question1.equals(question3));
    }
    @Test
    public void listRoll(){
        quiz.clear();
        Assert.assertTrue(quiz.drawQuestionSet(4).size() == 0);
        Assert.assertTrue(quiz.drawQuestionSet(-1).size() == 0);
        quiz.add("Question1");
        quiz.add("Question2");
        quiz.add("Question3");
        quiz.add("Question4");
        quiz.add("Question5");
        Assert.assertTrue(quiz.drawQuestionSet(5).size() == 5);
        Assert.assertTrue(quiz.drawQuestionSet(1).size() == 1);
        Assert.assertTrue(quiz.drawQuestionSet(2).size() == 2);
        Assert.assertTrue(quiz.drawQuestionSet(3).size() == 3);
        Assert.assertTrue(quiz.drawQuestionSet(4).size() == 4);
        Assert.assertTrue(quiz.drawQuestionSet(5).size() == 5);
        quiz.delete(4);
        Assert.assertTrue(quiz.drawQuestionSet(3).size() == 3);
        Assert.assertTrue(quiz.drawQuestionSet(5).size() == 0);
    }




}
