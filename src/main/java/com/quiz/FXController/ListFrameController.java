package com.quiz.FXController;

import com.quiz.FXManager.StageManager;
import com.quiz.DAO.DatabaseImpl;
import com.quiz.entity.Question;
import com.quiz.DAO.Quiz;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ListFrameController implements Initializable {

    private Quiz quiz;
    private StageManager manager;

    @FXML
    private ListView<Question> listFrameView;

    public ListFrameController(Quiz quiz, StageManager stageManager) {
        this.manager = stageManager;
        this.quiz = quiz;
    }
    @FXML
    public void listFrameDeleteQuestion() {
        if (!listFrameView.getSelectionModel().isEmpty()) {
            Alert deleteAlert = setConfirmationAlert("Delete question", "Do you want to delete question " + listFrameView.getSelectionModel().getSelectedItem().toString());
            Optional<ButtonType> result = deleteAlert.showAndWait();
            if (result.get() == ButtonType.OK) {
                if (quiz.delete(listFrameView.getSelectionModel().getSelectedItem())) {
                    if(quiz.getClass() == DatabaseImpl.class)
                        listFrameView.setItems(quiz.getList());
                    MainFrameController.numberValue.set(quiz.getList().size());
                    listFrameView.refresh();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Error");
                    alert.show();
                }
            }
            if (result.get() == ButtonType.CANCEL) {
                deleteAlert.close();
            }
        }
    }
    @FXML
    public void addItem(){
        manager.setDialog("/fxml/AddFrame.fxml", "Add Question");
        refreshList();
    }

    @FXML
    public void switchToQuizScene(){
        manager.switchScene("/fxml/MainFrame.fxml", "Quiz");
    }

    private void refreshList(){
        listFrameView.setItems(quiz.getList());
        listFrameView.scrollTo(quiz.getList().size());
        listFrameView.getSelectionModel().selectLast();
    }

    @FXML
    public void editItem(){
        if (!listFrameView.getSelectionModel().isEmpty()) {
            manager.setEditedQestion(listFrameView.getSelectionModel().getSelectedItem());
            manager.setDialog("/fxml/EditFrame.fxml", "Edit Question");
            refreshList();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listFrameView.setItems(quiz.getList());
    }

    public void clearListFrame() {
        Alert clearAlert = setConfirmationAlert("Clear List", "Do you want to clear the question list?");
        Optional<ButtonType> result = clearAlert.showAndWait();
        if (result.get() == ButtonType.CANCEL) {
            clearAlert.close();
        } else if (result.get() == ButtonType.OK) {
            quiz.clear();
            MainFrameController.numberValue.set(quiz.getList().size());
        }
        listFrameView.setItems(quiz.getList());
    }

    private Alert setConfirmationAlert(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(text);
        alert.setTitle(title);
        return alert;
    }
    
}
