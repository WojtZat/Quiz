package com.quiz.configuration;

import com.quiz.DAO.MemoryImpl;
import com.quiz.DAO.Quiz;
import com.quiz.entity.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.quiz")
public class springConfig {

    @Bean
    @Qualifier("memoryQuiz")
    public MemoryImpl memoryQuiz() {
        return new MemoryImpl(questionList());
    }

    @Bean
    public ObservableList<Question> questionList() {
        return FXCollections.observableArrayList();
    }


}
