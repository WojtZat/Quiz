package com.quiz.program;

import com.quiz.configuration.appConfiguration;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        appConfiguration config = new appConfiguration(primaryStage);
        config.loadMainFrame();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
