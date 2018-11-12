package com.quiz.DAO;


import com.quiz.entity.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Repository
@Scope("singleton")
public class DatabaseImpl implements Quiz {

    @Autowired
    private SessionFactory factory;

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public boolean add(String title, String text) {
        Session session = factory.getCurrentSession();
        Question question = new Question(title,text);
        session.save(question);
        return true;
    }

    @Override
    public boolean delete(int i) {
        Session session = factory.getCurrentSession();
        Question question  = session.get(Question.class, i);
        if((question == null)){
            return false;
        }
        else{
            session.delete(question);
            return true;
        }
    }

    private boolean canDraw(int numberOfQuestion){
        Session session = factory.getCurrentSession();
        List<Question> list = session.createQuery("from question_table").getResultList();
        return list.size() != 0 && list.size() >= numberOfQuestion;
    }

    private ObservableList<Question> rollArray(int number) {
        ObservableList<Question> questionsArray = FXCollections.observableArrayList();
        Session session = factory.getCurrentSession();
        List<Question> list = session.createQuery("from question_table").getResultList();
        int[] values = new Random().ints(0, list.size()).distinct().limit(number).toArray();
        for (int a : values) {
            questionsArray.add(list.get(a));
        }
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
        session.createQuery("delete from question_table").executeUpdate();
    }

    @Override
    public boolean delete(Question q) {
        Session session = factory.getCurrentSession();
        session.delete(q);
        return true;
    }

    @Override
    public ObservableList<Question> getList() {
        Session session= factory.getCurrentSession();
        List<Question> listQuerry = session.createQuery("from question_table").getResultList();
        ObservableList<Question> list = FXCollections.observableList(listQuerry);
        if(list.isEmpty())
            return FXCollections.emptyObservableList();
        return list;
    }

    @Override
    public boolean add(Question question) {
        Session session = factory.getCurrentSession();
        session.save(question);
        return true;
    }

    @Override
    public void editQuestion(Question oldQuestion, Question newQuestion) {
        Session session = factory.getCurrentSession();
        Question editedQuestion  = session.get(Question.class, oldQuestion.getId());
        editedQuestion.setQuestionText(newQuestion.getQuestionText());
        editedQuestion.setQuestionTitle(newQuestion.getQuestionTitle());
        editedQuestion.setAnswer(newQuestion.getAnswer());
    }


}
