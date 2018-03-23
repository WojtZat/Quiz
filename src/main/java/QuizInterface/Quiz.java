package QuizInterface;

import java.util.ArrayList;

public interface Quiz {
    public boolean add(String header , String text);
    public boolean delete(int i);
    public ArrayList<Question> drawQuestionSet(int numberOfQuestions);
    public void clear();
}
