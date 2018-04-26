import QuizModel.MemoryQuiz;
import QuizModel.Question;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QuizModelTest {

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("appContext.xml");
    MemoryQuiz quiz = context.getBean("memoryQuiz", MemoryQuiz.class);

    @Test
    public void addTest(){
        quiz.clear();
        quiz.add("Title1", "Question1");
        quiz.add("Title2", "Question2");
        quiz.add("Title3", "Question3");
        quiz.add("Title4", "Question4");
        quiz.add("Title5", "Question5");
        quiz.delete(0);
        quiz.delete(1);
        quiz.delete(2);
        quiz.delete(3);
        quiz.delete(4);
    }

    @Test
    public void addTest1(){
        quiz.clear();
        quiz.add("","");
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
    @Test
    public void addTest2(){
        quiz.clear();
        quiz.add("Title1", "Question1");
        quiz.add("Title2", "Question1");
        quiz.add("Title3", "Question1");
        quiz.add("Title4", "Question1");
        Assert.assertTrue(quiz.delete(0));
        Assert.assertTrue(quiz.delete(0));
    }

    @Test
    public void equalsTest(){
        Question question1 = new Question("Title1", "question1");
        Question question2 = new Question("Title1", "question1");
        Question question3 = new Question("Title3", "question3");
        Assert.assertTrue(question1.equals(question2));
        Assert.assertFalse(question1.equals(question3));
    }
    @Test
    public void listRoll(){
        quiz.clear();
        Assert.assertTrue(quiz.drawQuestionSet(4).size() == 0);
        Assert.assertTrue(quiz.drawQuestionSet(-1).size() == 0);
        quiz.add("Title1", "Question1");
        quiz.add("Title2", "Question2");
        quiz.add("Title3", "Question3");
        quiz.add("Title4", "Question4");
        quiz.add("Title5", "Question5");
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
    @Test
    public void singletonTest(){
        System.out.println(quiz.getList().size());
        quiz.add("Title1", "Question1");
        quiz.add("Title2", "Question2");
        quiz.add("Title3", "Question3");
        quiz.add("Title4", "Question4");
        quiz.add("Title5", "Question5");
        MemoryQuiz quiz2 = context.getBean("memoryQuiz", MemoryQuiz.class);
        Assert.assertTrue(quiz2.getList().size() == 5);
    }




}
