package com.quiz.DAL;

import com.quiz.entity.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.Random;

@Repository
@Scope("singleton")
public class MemoryModel implements Quiz {

    private final ObservableList<Question> questionList;

    @Autowired
    public MemoryModel(ObservableList<Question> questionList) {
        this.questionList = questionList;
    }

    @Override
    public boolean add(String header, String text) {
        if (header.isEmpty() || text.isEmpty())
            return false;
        Question newQuestion = new Question(header, text);
        if (!this.questionList.contains(newQuestion)) {
            questionList.add(newQuestion);
            return true;
        } else
            return false;
    }

    @Override
    public boolean add(Question question) {
        if (question.getText().isEmpty() || question.getTitle().isEmpty())
            return false;
        if (!this.questionList.contains(question)) {
            questionList.add(question);
            return true;
        } else
            return false;
    }

    @Override
    public boolean delete(int numberOfQuestion) {
        if (numberOfQuestion >= this.questionList.size() || numberOfQuestion < 0)
            return false;
        else {
            this.questionList.remove(numberOfQuestion);
            return true;
        }
    }

    @Override
    public boolean delete(Question q) {
        if (!this.questionList.contains(q))
            return false;
        else {
            this.questionList.remove(q);
            return true;
        }
    }

    @Override
    public ObservableList<Question> drawQuestionSet(int numberOfQuestions) {
        if (!canDraw(numberOfQuestions)) {
            return FXCollections.observableArrayList();
        } else {
            return rollArray(numberOfQuestions);
        }
    }

    private boolean canDraw(int number) {
        return number > 0 && this.questionList.size() >= number;
    }

    private ObservableList<Question> rollArray(int number) {
        ObservableList<Question> questionsArray = FXCollections.observableArrayList();
        int[] values = new Random().ints(0, this.questionList.size()).distinct().limit(number).toArray();
        for (int a : values) {
            questionsArray.add(this.questionList.get(a));
        }
        return questionsArray;
    }

    @Override
    public void clear() {
        this.questionList.clear();
    }

    public ObservableList<Question> getList() {
        return questionList;
    }

    @Override
    public void editQuestion(Question oldQuestion, Question editedQuestion) {
        int index = this.questionList.indexOf(oldQuestion);
        Question tempQuestion = this.questionList.get(index);
        tempQuestion.setTitle(editedQuestion.getTitle());
        tempQuestion.setText(editedQuestion.getText());
        tempQuestion.setAnswer(editedQuestion.getAnswer());
    }

}
