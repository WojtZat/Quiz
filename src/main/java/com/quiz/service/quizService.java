package com.quiz.service;

import com.quiz.entity.Question;
import javafx.collections.ObservableList;


//temporary quiz functionality
public interface quizService {
    boolean add(String header, String text);

    boolean delete(int i);

    ObservableList<Question> drawQuestionSet(int numberOfQuestions);

    void clear();

    boolean delete(Question q);

    ObservableList<Question> getList();

    boolean add(Question question);

    void editQuestion(Question oldQuestion, Question editedQuestion);
}
