package com.quiz.configuration;

import com.quiz.DAO.DatabaseImpl;
import com.quiz.DAO.Quiz;
import com.quiz.FXManager.StageManager;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppConfiguration {

    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    private Quiz quiz;
    private StageManager stageManager;


    public AppConfiguration(Stage primarystage) {
//        quiz = context.getBean("memoryImpl", MemoryImpl.class);
        quiz = context.getBean("databaseImpl", DatabaseImpl.class);
        stageManager = new StageManager(primarystage, quiz);
    }

    public void loadMainFrame() {
        stageManager.switchScene("/fxml/MainFrame.fxml", "Quiz");
    }

}
