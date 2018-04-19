package QuizInterface;

import java.io.Serializable;
import java.util.Objects;

public class Question implements Serializable {

    public String getQuestionText() {
        return questionText;
    }

    String questionText;

    String questionTitle;

    @Override
    public String toString() {
        return questionTitle;
    }

    public Question(String header, String text) {
        this.questionTitle = header;
        questionText = text;
    }

    public Question() {

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question question = (Question) o;
        return Objects.equals(questionText, question.questionText) &&
                Objects.equals(questionTitle, question.questionTitle);
    }

    @Override
    public int hashCode() {

        return Objects.hash(questionText, questionTitle);
    }
}
