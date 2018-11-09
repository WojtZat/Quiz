package com.quiz.service;

import com.quiz.DAO.Quiz;
import com.quiz.entity.Question;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class quizServiceImpl implements quizService {

    @Autowired
    @Qualifier("memoryQuiz")
    public Quiz quiz;
    {
    }

    @Override
    public boolean add(String header, String text) {
        return false;
    }

    @Override
    public boolean delete(int i) {
        return false;
    }

    @Override
    public ObservableList<Question> drawQuestionSet(int numberOfQuestions) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean delete(Question q) {
        return false;
    }

    @Override
    public ObservableList<Question> getList() {
        return null;
    }

    @Override
    public boolean add(Question question) {
        return false;
    }

    @Override
    public void editQuestion(Question oldQuestion, Question editedQuestion) {

    }
}
