package com.quiz.FXManager;

import com.quiz.FXController.AddFrameController;
import com.quiz.FXController.EditFrameController;
import com.quiz.FXController.ListFrameController;
import com.quiz.FXController.MainFrameController;
import com.quiz.entity.Question;
import com.quiz.service.QuizService;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class StageManager {

    private final Stage primaryStage;
    private final FXMLLoader FXMLLoader;
    private QuizService quiz;
    private Question editedQestion;

    public void setEditedQestion(Question editedQestion) {
        this.editedQestion = editedQestion;
    }

    public StageManager(Stage stage, QuizService quizConfig) {
        this.quiz = quizConfig;
        this.FXMLLoader = new FXMLLoader();
        this.primaryStage = stage;
        editedQestion = null;
    }

    public void switchScene(String view, String title) {
        Parent viewRootNodeHierarchy = loadViewNodeHierarchy(view);
        show(viewRootNodeHierarchy, title);
    }

    private Parent loadViewNodeHierarchy(String fxmlFilePath) {
        Parent rootNode = null;
        try {
            FXMLLoader.setRoot(null);
            FXMLLoader.setController(null);
            FXMLLoader.setLocation(getClass().getResource(fxmlFilePath));
            if (fxmlFilePath.matches("/fxml/ListFrame.fxml")) {
                FXMLLoader.setControllerFactory(c -> {
                    return new ListFrameController(quiz, this);
                });
            }
            if (fxmlFilePath.matches("/fxml/MainFrame.fxml")) {
                FXMLLoader.setControllerFactory(c -> {
                    return new MainFrameController(quiz, this);
                });
            }
            rootNode = FXMLLoader.load();
            Objects.requireNonNull(rootNode, "A Root FXML node must not be null");
        } catch (Exception exception) {
            exception.getStackTrace();
            System.out.println("failed to make a parent root");
        }
        return rootNode;
    }

    private void show(final Parent rootnode, String title) {
        Scene scene = prepareScene(rootnode);
        Parent root = FXMLLoader.getRoot();
        primaryStage.setTitle(title);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.centerOnScreen();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                primaryStage.close();
            }
        });
        try {
            primaryStage.show();
        } catch (Exception exception) {
            exception.getStackTrace();
        }
    }

    private Scene prepareScene(Parent rootnode) {
        Scene scene = primaryStage.getScene();
        if (scene == null) {
            scene = new Scene(rootnode);
        }
        scene.setRoot(rootnode);
        return scene;
    }

    public void setDialog(String view, String title) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(primaryStage.getOwner());
        dialog.setTitle(title);
        FXMLLoader.setRoot(null);
        FXMLLoader.setController(null);
        FXMLLoader.setLocation(getClass().getResource(view));
        if (view.matches("/fxml/AddFrame.fxml")) {
            FXMLLoader.setControllerFactory(c -> {
                return new AddFrameController(quiz);
            });
        }
        if (view.matches("/fxml/EditFrame.fxml"))
            FXMLLoader.setControllerFactory(c -> {
                return new EditFrameController(quiz, editedQestion);
            });
        try {
            dialog.getDialogPane().setContent(FXMLLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.setX(primaryStage.getX() + 200);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                stage.close();
            }
        });
        Optional<ButtonType> result = dialog.showAndWait();
    }

}
