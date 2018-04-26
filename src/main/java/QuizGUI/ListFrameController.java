package QuizGUI;

import QuizModel.Question;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ListFrameController implements Initializable {

    @FXML
    private ListView<Question> listFrameView;

    @FXML
    public void listFrameDeleteQuestion() {
        MainFrameController mainFrameController = getMainController();
        Alert deleteAlert = setConfirmationAlert("Delete question", "Do you want to delete question " + listFrameView.getSelectionModel().getSelectedItem().toString() );
        Optional<ButtonType> result = deleteAlert.showAndWait();
        if(result.get() == ButtonType.OK) {
            if (mainFrameController.deleteQuestion(listFrameView.getSelectionModel().getSelectedItem())) {
                listFrameView.refresh();
                Alert informationAlert = setInformationAlert("Delete question", "Delete succesfull");
                informationAlert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Error");
                alert.show();
            }
        }
        if (result.get() == ButtonType.CANCEL){
            deleteAlert.close();
        }
    }

    private void setListToMainList() {
        MainFrameController mainMainFrameController = getMainController();
        listFrameView.setItems(mainMainFrameController.getList());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setListToMainList();
    }
    public void clearListListFrame() {
        Alert clearAlert = setConfirmationAlert("Clear List", "Do you want to clear the question list?");
        Optional<ButtonType> result = clearAlert.showAndWait();
        if (result.get() == ButtonType.CANCEL){
         clearAlert.close();
        }
        else if (result.get() == ButtonType.OK) {
            MainFrameController mainFrameController = getMainController();
            mainFrameController.clearList();
            Alert confirmation = setInformationAlert("Clear List", "List cleared");
            confirmation.show();
        }

    }
    private Alert setWarningAlert(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(text);
        alert.setTitle(title);
        return alert;
    }
    private Alert setConfirmationAlert(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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
}
