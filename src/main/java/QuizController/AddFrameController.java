package QuizController;

import QuizModel.MemoryQuiz;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddFrameController implements Initializable {



    @FXML
    private TextArea addTitleText;
    @FXML
    private TextArea addBodyText;


    public void addFrameAddQuestion() {
        if (!addTitleText.getText().isEmpty() && !addBodyText.getText().isEmpty()) {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("appContext.xml");
            MemoryQuiz quiz = context.getBean("memoryQuiz", MemoryQuiz.class);
            quiz.add(addTitleText.getText(), addBodyText.getText());
            System.out.println(quiz.getList().size());
            Alert addAlert = setInformationAlert("Add question", "Question " + addTitleText.getText().trim() + " added");
            addAlert.show();

        }
        else
        System.out.println("error");
//        Alert addAlert = setInformationAlert("Add question", "Question " + addTitleText.getText().trim() + " added");
//        addAlert.show();
    }

    public void addQuestion() {
        MainFrameController mainFrameController = getMainController();
        mainFrameController.addQuestion(addTitleText.getText(), addBodyText.getText());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        addButton.disableProperty().bind(
//                Bindings.or(addTitleText.textProperty().isEmpty(), addBodyText.textProperty().isEmpty())
//        );
    }

    private Alert setWarningAlert(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(text);
        alert.setTitle(title);
        return alert;
    }

    private MainFrameController getMainController() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/MainFrame.fxml"));
        try {
            loader.load();
            return loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            Alert getControllerAlert = setWarningAlert("Connection Error", "Failed to connect to Main MainFrameController");
            getControllerAlert.show();
        }
        return null;
    }

    private Alert setInformationAlert(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(text);
        alert.setTitle(title);
        return alert;
    }
}
