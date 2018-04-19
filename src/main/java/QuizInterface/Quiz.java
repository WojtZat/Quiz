package QuizInterface;

import javafx.collections.ObservableList;

import java.util.ArrayList;

public interface Quiz {
    public boolean add(String header , String text);
    public boolean delete(int i);
    public ObservableList<Question> drawQuestionSet(int numberOfQuestions);
    public void clear();
    public boolean delete(Question q);
    public ObservableList<Question> getList();
    public boolean add(Question question);
}
