package com.quiz.configuration;

import com.quiz.FXManager.StageManager;
<<<<<<< master
=======
import com.quiz.service.QuizService;
>>>>>>> local
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppConfiguration {

    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
<<<<<<< master
    private Quiz quiz;
=======

//    @Autowired
//    @Qualifier("quizService")
    private QuizService quiz;
>>>>>>> local
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
