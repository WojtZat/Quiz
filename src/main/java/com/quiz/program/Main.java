package com.quiz.program;

import com.quiz.configuration.AppConfiguration;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        AppConfiguration config = new AppConfiguration(primaryStage);
        config.loadMainFrame();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
