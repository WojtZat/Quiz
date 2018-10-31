package com.quiz.configuration;

import com.quiz.DAO.DatabaseImpl;
import com.quiz.DAO.MemoryImpl;
import com.quiz.DAO.Quiz;
import com.quiz.program.StageManager;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;


public class Configuration {

    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(springConfig.class);
    //    private ClassPathXmlApplicationContext context;
    private Quiz quiz;
    private StageManager stageManager;


    public Configuration(Stage primarystage){
//        context = new ClassPathXmlApplicationContext("appContext.xml");
        quiz = context.getBean("memoryImpl", MemoryImpl.class);
//        quiz = context.getBean("databaseImpl", DatabaseImpl.class );
        stageManager = new StageManager(primarystage , quiz);

    }

    public void loadMainFrame(){
        stageManager.switchScene("/fxml/MainFrame.fxml", "Quiz");
    }

}
