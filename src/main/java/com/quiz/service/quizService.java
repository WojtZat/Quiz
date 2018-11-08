package com.quiz.service;

import com.quiz.DAO.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class quizService {

    @Autowired
    @Qualifier("memoryQuiz")
    public Quiz quiz;

    {
    }
}
