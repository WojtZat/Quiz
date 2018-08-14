package Program;

import QuizModel.DbQuiz;
import QuizModel.Quiz;
import javafx.stage.Stage;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Configuration {

    ClassPathXmlApplicationContext context;
    Quiz quiz;
    StageManager stageManager;


    public Configuration(Stage primarystage){
        context = new ClassPathXmlApplicationContext("appContext.xml");
//        quiz = context.getBean("memoryQuiz", MemoryQuiz.class);
        quiz = context.getBean("dbQuiz",DbQuiz.class );
        stageManager = new StageManager(primarystage , quiz);

    }

    public void loadMainFrame(){
        stageManager.switchScene("/fxml/MainFrame.fxml", "Quiz");
    }
}
