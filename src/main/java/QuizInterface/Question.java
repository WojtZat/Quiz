package QuizInterface;

import java.io.Serializable;
import java.util.Objects;

public class Question implements Serializable {

    String questionText;

    String header;

    @Override
    public String toString() {
        return "Question{" +
                "questionText='" + questionText + '\'' +
                ", header='" + header + '\'' +
                '}';
    }

    public Question(String header, String text) {
        this.header = header;
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
                Objects.equals(header, question.header);
    }

    @Override
    public int hashCode() {

        return Objects.hash(questionText, header);
    }
}
