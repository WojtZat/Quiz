import com.quiz.DAO.DatabaseImpl;
import com.quiz.DAO.MemoryImpl;
import com.quiz.entity.Question;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QuizModelTest {

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("appContext.xml");
    MemoryImpl quiz = context.getBean("memoryImpl", MemoryImpl.class);
    DatabaseImpl quiz2 = context.getBean("databaseImpl", DatabaseImpl.class );


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
        quiz.clear();
        quiz.add("Title1", "Question1");
        quiz.add("Title2", "Question2");
        quiz.add("Title3", "Question3");
        quiz.add("Title4", "Question4");
        quiz.add("Title5", "Question5");
        if(quiz.getClass().equals(DatabaseImpl.class)){
            DatabaseImpl quiz2 = context.getBean("databaseImpl", DatabaseImpl.class);
            Assert.assertTrue(quiz2.getList().size() == 5);
        }
        if(quiz.getClass().equals(MemoryImpl.class)) {
            MemoryImpl quiz2 = context.getBean("memoryImpl", MemoryImpl.class);
            Assert.assertTrue(quiz2.getList().size() == 5);
        }
        quiz.clear();
    }

    @Test
    public void editTest(){
        quiz.clear();
        Question q = new Question("Title1", "Question1");
        quiz.add(q);
        Question editedQ = new Question("EditedTitle1", "EditedQuestion2");
        editedQ.setAnswer("editedAnswer!");
        System.out.printf(q.getQuestionText()+"\n");
        System.out.printf(editedQ.getQuestionText()+"\n");
        System.out.println("----------------");
        Question e = null;
        quiz.editQuestion(q,editedQ );
        if(quiz.getClass().equals(MemoryImpl.class)){
            int i = quiz.getList().indexOf(q);
            e = quiz.getList().get(i);
        }
        if(quiz.getClass().equals(DatabaseImpl.class)){
            e = quiz.getList().get(0);
        }
        for(Question a : quiz.getList())
            System.out.println(a.getQuestionText()+ " " + a.getQuestionTitle() + " " + a.getAnswer() + "\n");
        Assert.assertTrue(e.getQuestionText().equals(editedQ.getQuestionText()));
    }




}
