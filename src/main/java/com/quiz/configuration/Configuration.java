package com.quiz.configuration;

import com.quiz.DAO.DatabaseImpl;
import com.quiz.DAO.Quiz;
import com.quiz.FXManager.StageManager;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

//@Component
public class Configuration {

    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(springConfig.class);

    //    @Autowired
//    @Qualifier("databaseQuiz")
    private Quiz quiz;

    private StageManager stageManager;


    public Configuration(Stage primarystage){
//        quiz = context.getBean("memoryImpl", MemoryImpl.class);
        quiz = context.getBean("databaseImpl", DatabaseImpl.class);
        stageManager = new StageManager(primarystage , quiz);

    }

    public void loadMainFrame(){
        stageManager.switchScene("/fxml/MainFrame.fxml", "Quiz");
    }

}
