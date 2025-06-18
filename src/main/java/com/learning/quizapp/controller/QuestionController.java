package com.learning.quizapp.controller;

import com.learning.quizapp.model.Question;
import com.learning.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService quizService;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "ok";
    }

    @GetMapping("/questions")
    public List<Question> getAllQuestions() {
        return quizService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public List<Question> getQuestionsByCategory(@PathVariable String category) {
        return quizService.getQuestionsByCategory(category);
    }

    @PostMapping("/add-question")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        quizService.addQuestion(question);
        return new ResponseEntity<>("question is added", HttpStatus.OK);
    }
}
