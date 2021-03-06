package com.quiz.configuration;

import com.quiz.FXManager.StageManager;
import com.quiz.service.QuizService;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppConfiguration {

    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

    private QuizService quiz;

    private StageManager stageManager;

    public AppConfiguration(Stage primarystage) {
        quiz = context.getBean("quizService", QuizService.class);
        stageManager = new StageManager(primarystage, quiz);
    }

    public void loadMainFrame() {
        stageManager.switchScene("/fxml/MainFrame.fxml", "Quiz");
    }

}
