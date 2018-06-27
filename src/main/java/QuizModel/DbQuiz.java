package QuizModel;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Closeable;
import java.util.List;
import java.util.Random;

@Component
@Scope("singleton")
public class DbQuiz implements Quiz {

    private static SessionFactory factory;

    public DbQuiz() {
        factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Question.class).buildSessionFactory();
    }

    @Override
    public boolean add(String title, String text) {
        Session session = factory.getCurrentSession();
        Question question = new Question(title,text);
        session.beginTransaction();
        session.save(question);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(int i) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Question question  = session.get(Question.class, i);
        if((question == null)){
            session.close();
            return false;
        }
        else{
            session.delete(question);
            session.getTransaction().commit();
            session.close();
            return true;
        }
    }

    private boolean canDraw(int numberOfQuestion){
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        List<Question> list = session.createQuery("from question_table").getResultList();
        session.getTransaction().commit();
        if(list.size() == 0 || list.size() < numberOfQuestion){
            session.close();
            return false;
        }else
            session.close();
            return true;
    }

    private ObservableList<Question> rollArray(int number) {
        ObservableList<Question> questionsArray = FXCollections.observableArrayList();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        List<Question> list = session.createQuery("from question_table").getResultList();
        session.getTransaction().commit();
        int[] values = new Random().ints(0, list.size()).distinct().limit(number).toArray();
        for (int a : values) {
            questionsArray.add(list.get(a));
        }
        session.close();
        return questionsArray;
    }

    @Override
    public ObservableList<Question> drawQuestionSet(int numberOfQuestions) {
        if (!canDraw(numberOfQuestions)) {
            return FXCollections.observableArrayList();
        } else {
            return rollArray(numberOfQuestions);
        }
    }

    @Override
    public void clear() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete from question_table").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public boolean delete(Question q) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.delete(q);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public ObservableList<Question> getList() {
        Session session= factory.getCurrentSession();
        session.beginTransaction();
        List<Question> listQuerry = session.createQuery("from question_table").getResultList();
        session.getTransaction().commit();
        session.close();
        ObservableList<Question> list = FXCollections.observableList(listQuerry);
        if(list.isEmpty())
            return FXCollections.emptyObservableList();
        return list;
    }

    @Override
    public boolean add(Question question) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.save(question);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public void editQuestion(Question oldQuestion, Question newQuestion) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Question editedQuestion  = session.get(Question.class, oldQuestion.getId());
        editedQuestion.setQuestionText(newQuestion.getQuestionText());
        editedQuestion.setQuestionTitle(newQuestion.getQuestionTitle());
        editedQuestion.setAnswer(newQuestion.getAnswer());
        session.getTransaction().commit();
        session.close();
    }

    public static  void close(){
        factory.close();
    }

}
