package QuizGUI;

import QuizInterface.MemoryQuiz;
import QuizInterface.Question;
import QuizInterface.Quiz;
import com.sun.xml.internal.ws.developer.MemberSubmissionAddressing;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Quiz quiz;

    @FXML
    private Button addButton;
    @FXML
    private GridPane MainFrame;
    @FXML
    private ListView<Question> listView;
    @FXML
    private TextField rollNumberText;
    @FXML
    private Button mainFrameRollButton;
    @FXML
    private TextArea mainViewTextArea;

    ValidationSupport validation;

    public Controller(){
        quiz = new MemoryQuiz();
        validation = new ValidationSupport();
    }

    @FXML
    public void showAddFrameDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(MainFrame.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/AddFrame.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.APPLY);
        dialog.getDialogPane().getButtonTypes().add(addButtonType);
        Node addButton = dialog.getDialogPane().lookupButton(addButtonType);
        AddFrameController controller = fxmlLoader.getController();
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.get() == addButtonType) {
            System.out.println("Asdasdasdasdsad");
//            quiz.add(controller.addFrameAddQuestion());
            controller.addQuestion();
        }else if(result.get() == ButtonType.CANCEL ) {
            System.out.println("Cancel pressed");
        }
    }

    @FXML
    public void showListFrameDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(MainFrame.getScene().getWindow());
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/ListFrame.fxml"));
            dialog.getDialogPane().setContent(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        dialog.showAndWait();
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
    public void hadleListClickView() {
        Question question = listView.getSelectionModel().getSelectedItem();
        mainViewTextArea.setText(question.getQuestionText());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        quiz.add("Title1", "Question1" + '\n' + "-asd");
        quiz.add("Title2", "Question2");
        quiz.add("Title3", "Question3");
        quiz.add("Title4", "Question4");
        quiz.add("Title5", "Question5");
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

    public boolean deleteQuestion(Question question){
        return this.quiz.delete(question);
    }

    public ObservableList<Question> getList(){
        return this.quiz.getList();
    }
    public void clearList(){
        System.out.println(quiz.getList().size());
        quiz.clear();
        System.out.println(quiz.getList().size());
    }

    public void addQuestion(String title, String text){
        quiz.add(title, text);
    }
}
