package com.quiz.configuration;

import com.quiz.FXManager.StageManager;
<<<<<<< HEAD
<<<<<<< master
=======
import com.quiz.service.QuizService;
>>>>>>> local
=======
import com.quiz.service.QuizService;
import com.quiz.service.QuizServiceImpl;
>>>>>>> 76647eea68cb6a7c610218d3a2aff727e91fe640
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppConfiguration {

    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
<<<<<<< HEAD
<<<<<<< master
    private Quiz quiz;
=======

//    @Autowired
//    @Qualifier("quizService")
    private QuizService quiz;
>>>>>>> local
=======

    @Autowired
    @Qualifier("quizService")
    private QuizService quiz;
>>>>>>> 76647eea68cb6a7c610218d3a2aff727e91fe640
    private StageManager stageManager;


    public AppConfiguration(Stage primarystage) {
//        quiz = context.getBean("memoryImpl", MemoryImpl.class);
        quiz = context.getBean("quizService", QuizService.class);
        stageManager = new StageManager(primarystage, quiz);
    }

    public void loadMainFrame() {
        stageManager.switchScene("/fxml/MainFrame.fxml", "Quiz");
    }

}
