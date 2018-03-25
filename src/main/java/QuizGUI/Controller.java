package QuizGUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Optional;

public class Controller {

    @FXML
    private GridPane MainFrame;

    @FXML
    public void showAddFrameDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(MainFrame.getScene().getWindow());
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/AddFrame.fxml"));
            dialog.getDialogPane().setContent(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("ok Pressed");
        } else {
            System.out.println("cancel pressed");
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
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("ok Pressed");
        } else {
            System.out.println("cancel pressed");
        }
    }
}
