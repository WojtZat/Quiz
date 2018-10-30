package com.quiz.program;

import com.quiz.DAO.DatabaseImpl;
import com.quiz.DAO.Quiz;
import javafx.stage.Stage;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Configuration {

    ClassPathXmlApplicationContext context;
    Quiz quiz;
    StageManager stageManager;


    public Configuration(Stage primarystage){
        context = new ClassPathXmlApplicationContext("appContext.xml");
//        quiz = context.getBean("memoryImpl", MemoryImpl.class);
        quiz = context.getBean("databaseImpl", DatabaseImpl.class );
        stageManager = new StageManager(primarystage , quiz);

    }

    public void loadMainFrame(){
        stageManager.switchScene("/fxml/MainFrame.fxml", "Quiz");
    }
}
