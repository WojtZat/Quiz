import com.quiz.DAO.DatabaseImpl;
import com.quiz.DAO.MemoryImpl;
import com.quiz.DAO.Quiz;
import com.quiz.configuration.springConfig;
import com.quiz.entity.Question;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class QuizModelTest {

    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(springConfig.class);

//    private Quiz quiz  = context.getBean("memoryImpl",MemoryImpl.class);

    private Quiz quiz = context.getBean("databaseImpl", DatabaseImpl.class);


    @Test
    public void addTest() {

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
    public void addTest1() {
        quiz.clear();
        quiz.add("", "");
        quiz.delete(0);
    }

    @Test
    public void deleteUnexistingElements() {
        Assert.assertFalse(quiz.delete(-1));
    }

    @Test
    public void deleteUnexistingElements2() {
        Assert.assertFalse(quiz.delete(100));
    }

    @Test
    public void equalsTest() {
        Question question1 = new Question("Title1", "question1");
        Question question2 = new Question("Title1", "question1");
        Question question3 = new Question("Title3", "question3");
        Assert.assertEquals(question1, question2);
        Assert.assertNotEquals(question1, question3);
    }

    @Test
    public void listRoll() {
        quiz.clear();
        Assert.assertEquals(0, quiz.drawQuestionSet(4).size());
        Assert.assertEquals(0, quiz.drawQuestionSet(-1).size());
        quiz.add("Title1", "Question1");
        quiz.add("Title2", "Question2");
        quiz.add("Title3", "Question3");
        quiz.add("Title4", "Question4");
        quiz.add("Title5", "Question5");
        Assert.assertEquals(5, quiz.drawQuestionSet(5).size());
        Assert.assertEquals(1, quiz.drawQuestionSet(1).size());
        Assert.assertEquals(2, quiz.drawQuestionSet(2).size());
        Assert.assertEquals(3, quiz.drawQuestionSet(3).size());
        Assert.assertEquals(4, quiz.drawQuestionSet(4).size());
        Assert.assertEquals(5, quiz.drawQuestionSet(5).size());
        quiz.delete(4);
        Assert.assertEquals(3, quiz.drawQuestionSet(3).size());
        Assert.assertEquals(0, quiz.drawQuestionSet(5).size());
    }

    @Test
    public void singletonTest() {
        quiz.clear();
        quiz.add("Title1", "Question1");
        quiz.add("Title2", "Question2");
        quiz.add("Title3", "Question3");
        quiz.add("Title4", "Question4");
        quiz.add("Title5", "Question5");
        if (quiz.getClass().equals(DatabaseImpl.class)) {
            DatabaseImpl quiz2 = context.getBean("databaseImpl", DatabaseImpl.class);
            Assert.assertEquals(5, quiz2.getList().size());
        }
        if (quiz.getClass().equals(MemoryImpl.class)) {
            MemoryImpl quiz2 = context.getBean("memoryImpl", MemoryImpl.class);
            Assert.assertEquals(5, quiz2.getList().size());
        }
        quiz.clear();
    }

    @Test
    public void editTest() {
        quiz.clear();
        Question q = new Question("Title1", "Question1");
        quiz.add(q);
        Question editedQ = new Question("EditedTitle1", "EditedQuestion2");
        editedQ.setAnswer("editedAnswer!");
        System.out.print(q.getQuestionText() + "\n");
        System.out.print(editedQ.getQuestionText() + "\n");
        System.out.println("----------------");
        Question e = null;
        quiz.editQuestion(q, editedQ);
        if (quiz.getClass().equals(MemoryImpl.class)) {
            int i = quiz.getList().indexOf(q);
            e = quiz.getList().get(i);
        }
        if (quiz.getClass().equals(DatabaseImpl.class)) {
            e = quiz.getList().get(0);
        }
        for (Question a : quiz.getList())
            System.out.println(a.getQuestionText() + " " + a.getQuestionTitle() + " " + a.getAnswer() + "\n");
        assert e != null;
        Assert.assertEquals(e.getQuestionText(), editedQ.getQuestionText());
    }


}
