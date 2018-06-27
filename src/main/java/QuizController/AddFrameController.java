package QuizController;

import Program.StageManager;
import QuizModel.Question;
import QuizModel.Quiz;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddFrameController implements Initializable {

    private Quiz quiz;

    @FXML
    private TextArea addTitleText;
    @FXML
    private TextArea addBodyText;
    @FXML
    private TextArea answerArea;

    public AddFrameController(Quiz quiz) {
        this.quiz = quiz;
    }

    public void addFrameAddQuestion() {
        if (!addTitleText.getText().isEmpty() && !addBodyText.getText().isEmpty()) {
            Question question = new Question(addTitleText.getText(), addBodyText.getText());
            if(!answerArea.getText().isEmpty())
                question.setAnswer(answerArea.getText());
            quiz.add(question);
            addTitleText.clear();
            answerArea.clear();
            addBodyText.clear();
            MainFrameController.numberValue.set(quiz.getList().size());
            Alert addAlert = setInformationAlert("Add question", "Question " + addTitleText.getText().trim() + " added");
            addAlert.show();
        } else {
            Alert errorAlert = setWarningAlert("Add question", "Question " + addTitleText.getText().trim() + " cant be added!");
            errorAlert.show();
        }
    }
    public void addFrameCancel(){
        Stage stage = (Stage )addTitleText.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private Alert setWarningAlert(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(text);
        alert.setTitle(title);
        return alert;
    }

    private Alert setInformationAlert(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(text);
        alert.setTitle(title);
        return alert;
    }

}