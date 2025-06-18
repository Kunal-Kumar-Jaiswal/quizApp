package com.learning.quizapp.controller;

import com.learning.quizapp.model.QuestionWrapper;
import com.learning.quizapp.model.Quiz;
import com.learning.quizapp.service.QuizService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<?> createQuiz(@RequestParam String title, @RequestParam String category, @RequestParam int numQ) {
        log.info(title+" "+category+" "+numQ);
        boolean flag = quizService.createQuiz(title,category,numQ);
        if(flag) {
            return new ResponseEntity<>("quiz is created", HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("quiz is not created", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("get/{id}")
    public ResponseEntity<?> getQuizById(@PathVariable int id) {
        List<QuestionWrapper> questions = quizService.getQuizById(id);
        if(questions.isEmpty()) {
            return new ResponseEntity<>("can't find id",HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(questions,HttpStatus.OK);
        }
    }
}
