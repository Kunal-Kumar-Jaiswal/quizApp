package com.learning.quizapp.service;

import com.learning.quizapp.model.Question;
import com.learning.quizapp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository sqlDdRepository;

    public List<Question> getAllQuestions() {
        return sqlDdRepository.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
        return sqlDdRepository.findByCategory(category);
    }

    public void addQuestion(Question question) {
        sqlDdRepository.save(question);
    }
}
