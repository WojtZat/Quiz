package com.quiz.DAL;

import com.quiz.entity.Question;
import javafx.collections.ObservableList;

public interface Quiz {
    boolean add(String header, String text);

    boolean delete(int i);

    ObservableList<Question> drawQuestionSet(int numberOfQuestions);

    void clear();

    boolean delete(Question q);

    ObservableList<Question> getList();

    boolean add(Question question);

    void editQuestion(Question oldQuestion, Question editedQuestion);

    // try fixing ObservableList into casual list , then convert into observable for fx usage only
}
