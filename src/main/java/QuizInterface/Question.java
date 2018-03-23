package QuizInterface;

import java.io.Serializable;
import java.util.Objects;

public class Question implements Serializable {

    String questionText;

    public Question(String text){
        questionText = text;
    }
    public Question(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question question = (Question) o;
        return Objects.equals(questionText, question.questionText);
    }

    @Override
    public int hashCode() {

        return Objects.hash(questionText);
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionText='" + questionText + '\'' +
                '}';
    }
}
