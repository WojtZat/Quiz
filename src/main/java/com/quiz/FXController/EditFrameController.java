package com.quiz.FXController;

import com.quiz.entity.Question;
import com.quiz.DAO.Quiz;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditFrameController implements Initializable {

    private Quiz quiz;
    private Question question;

    @FXML
    private TextArea editTitleText;
    @FXML
    private TextArea editBodyText;
    @FXML
    private TextArea answerEditArea;

    public EditFrameController(Quiz quiz, Question question) {
        this.quiz = quiz;
        this.question = question;
    }

    @FXML
    public void editQuestion() {
        if (!editTitleText.getText().isEmpty() && !editBodyText.getText().isEmpty()) {
            Question editedQuestion = new Question(editTitleText.getText(), editBodyText.getText());
            editedQuestion.setAnswer(answerEditArea.getText());
            quiz.editQuestion(this.question, editedQuestion);
            editFrameCancel();
        }
    }

    @FXML
    public void editFrameCancel() {
        Stage stage = (Stage) answerEditArea.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        editTitleText.setText(question.getQuestionTitle());
        editBodyText.setText(question.getQuestionText());
        if (!question.getAnswer().isEmpty())
            answerEditArea.setText(question.getAnswer());
    }

}
