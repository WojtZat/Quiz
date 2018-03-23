package QuizInterface;

import java.util.ArrayList;
import java.util.Random;

public class MemoryQuiz implements Quiz {

    private ArrayList<Question> questionList;

    private static int numberOfQuestions;

    public MemoryQuiz() {
        this.questionList = new ArrayList<>();
        numberOfQuestions = 0;
    }

    public static int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    @Override
    public boolean add(String header, String text) {
        if (header.isEmpty() || text.isEmpty())
            return false;
        Question newQuestion = new Question(header, text);
        if (!this.questionList.contains(newQuestion)) {
            questionList.add(newQuestion);
            numberOfQuestions++;
            return true;
        } else
            return false;
    }

    @Override
    public boolean delete(int number) {
        if (number >= this.questionList.size() || number < 0)
            return false;
        else {
            this.questionList.remove(number);
            numberOfQuestions--;
            return true;
        }
    }

    private boolean canDraw(int number) {
        return number > 0 && this.questionList.size() >= number;
    }

    @Override
    public ArrayList<Question> drawQuestionSet(int numberOfQuestions) {
        if (!canDraw(numberOfQuestions)) {
            return new ArrayList<>();
        } else {
            return rollArray(numberOfQuestions);
        }
    }

    private ArrayList<Question> rollArray(int number) {
        ArrayList<Question> questionsArray = new ArrayList<>();
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
}
