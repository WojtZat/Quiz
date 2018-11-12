package com.quiz.service;

import com.quiz.DAO.Quiz;
import com.quiz.entity.Question;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuizServiceImpl implements QuizService {

    @Autowired
//    @Qualifier("memoryQuiz")
    @Qualifier("databaseQuiz")
    public Quiz quiz;

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }


    @Override
    public boolean add(String header, String text) {
        return quiz.add(header, text);
    }

    @Override
    public boolean delete(int i) {
        return quiz.delete(i);
    }

    @Override
    public ObservableList<Question> drawQuestionSet(int numberOfQuestions) {
        return quiz.drawQuestionSet(numberOfQuestions);
    }

    @Override
    public void clear() {
        quiz.clear();
    }

    @Override
    public boolean delete(Question q) {
        return quiz.delete(q);
    }

    @Override
    public ObservableList<Question> getList() {
        return quiz.getList();
    }

    @Override
    public boolean add(Question question) {
        return quiz.add(question);
    }

    @Override
    public void editQuestion(Question oldQuestion, Question editedQuestion) {
        quiz.editQuestion(oldQuestion, editedQuestion);
    }
}
