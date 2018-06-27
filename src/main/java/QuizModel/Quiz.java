package QuizModel;

import javafx.collections.ObservableList;

public interface Quiz {
    public boolean add(String header , String text);
    public boolean delete(int i);
    public ObservableList<Question> drawQuestionSet(int numberOfQuestions);
    public void clear();
    public boolean delete(Question q);
    public ObservableList<Question> getList();
    public boolean add(Question question);
    public void editQuestion(Question oldQuestion, Question editedQuestion);
}
