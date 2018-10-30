package com.quiz.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "question_table")
public class Question implements Serializable {
    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    public int getId() {
        return id;
    }

    public Question(String questionTitle, String questionText) {
        this.questionTitle = questionTitle;
        this.questionText = questionText;
        this.answer = "";
    }

    public Question() {

    }


    @Column(name = "answer", nullable = false)
    private String answer;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Column(name = "title", nullable = false, unique = true)
    private String questionTitle;

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    @Column(name = "text", nullable = false)
    private String questionText;

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question question = (Question) o;
        return id == question.id &&
                Objects.equals(questionTitle, question.questionTitle) &&
                Objects.equals(questionText, question.questionText) &&
                Objects.equals(answer, question.answer);
    }

    @Override
    public String toString() {
        return questionTitle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionTitle, questionText, answer);
    }


}
