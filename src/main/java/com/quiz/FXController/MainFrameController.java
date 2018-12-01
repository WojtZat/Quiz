package com.quiz.FXController;

import com.quiz.FXManager.StageManager;
import com.quiz.entity.Question;
import com.quiz.service.QuizService;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.net.URL;
import java.util.ResourceBundle;


public class MainFrameController implements Initializable {

    public QuizService quiz;

    private ValidationSupport validation;


    private StageManager manager;

    @FXML
    private ListView<Question> listView;
    @FXML
    private TextField rollNumberText;
    @FXML
    private Button mainFrameRollButton;
    @FXML
    private TextArea mainViewTextArea;
    @FXML
    private Label numberLabel;
    @FXML
    private TextArea answerTextArea;
    @FXML
    private TitledPane answerAccordion;

    public static IntegerProperty numberValue;

    public MainFrameController(QuizService quiz, StageManager stageManager) {
        this.manager = stageManager;
        this.quiz = quiz;
        validation = new ValidationSupport();
        numberValue = new SimpleIntegerProperty(quiz.getList().size());
    }


    @FXML
    public void switchToListScene() {
        manager.switchScene("/fxml/ListFrame.fxml", "ListView");
    }

    @FXML
    public void rollQuestionSet() {
        if (quiz.drawQuestionSet(Integer.parseInt(rollNumberText.getText())).isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Wrong number of questions");
            alert.setTitle("Invalid number");
            alert.show();
        } else {
            listView.setItems(quiz.drawQuestionSet(Integer.parseInt(rollNumberText.getText())));
        }
    }

    @FXML
    public void handleListClickView() {
        if (!listView.getItems().isEmpty()) {
            Question question = listView.getSelectionModel().getSelectedItem();
            mainViewTextArea.setText(question.getText());
            answerAccordion.setDisable(false);
            answerTextArea.setText(question.getAnswer());
            if (listView.getSelectionModel().getSelectedItem().getAnswer().isEmpty() ||
                    listView.getSelectionModel().getSelectedItem().getAnswer().equals("")){
                answerAccordion.setDisable(true);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (numberValue != null)
            numberLabel.textProperty().bind(numberValue.asString());
        else
            numberLabel.setText("0");
        rollNumberText.disableProperty().bind(
                Bindings.equal(0, numberValue)
        );
        if (quiz.getList().isEmpty()) {
            mainViewTextArea.setText("Your list is empty!\nAdd new questions to enable quiz.");
        } else if (!quiz.getList().isEmpty()) {
            final Tooltip tooltip = new Tooltip();
            tooltip.setText("Pick number of question");
            rollNumberText.setTooltip(tooltip);
        }

        rollNumberText.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,4}")) {
                rollNumberText.setText(oldValue);
            }
        });
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        validation.registerValidator(rollNumberText, Validator.createEmptyValidator("Number is required"));
        mainFrameRollButton.disableProperty().bind(
                Bindings.isEmpty(rollNumberText.textProperty())
        );
    }


}
