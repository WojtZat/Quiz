package com.quiz.program;

import com.quiz.DAO.DatabaseImpl;
import com.quiz.configuration.Configuration;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage primaryStage) {
        Configuration config = new Configuration(primaryStage);
        config.loadMainFrame();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        DatabaseImpl.close();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
