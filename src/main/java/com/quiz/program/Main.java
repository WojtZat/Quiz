package com.quiz.program;

import com.quiz.configuration.Configuration;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Configuration config = new Configuration(primaryStage);
        config.loadMainFrame();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
