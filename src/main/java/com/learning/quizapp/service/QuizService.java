package com.learning.quizapp.service;

import com.learning.quizapp.model.Question;
import com.learning.quizapp.model.QuestionWrapper;
import com.learning.quizapp.model.Quiz;
import com.learning.quizapp.model.SubmitResponse;
import com.learning.quizapp.repository.QuestionRepository;
import com.learning.quizapp.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public boolean createQuiz(String title, String category, int totalQuestions) {
        List<Question> questionList = questionRepository.findRandomQuestionsByCategory(category,totalQuestions);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setCategory(category);
        quiz.setQuestions(questionList);
        quizRepository.save(quiz);
        return true;
    }

    public List<QuestionWrapper> getQuizById(int id) {
        Quiz quiz = quizRepository.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        List<QuestionWrapper> questionFromDB = new ArrayList<>();

        for(Question question: questions) {
            questionFromDB.add(new QuestionWrapper(question.getId(),question.getQuestionTitle(),question.getOption1(),question.getOption2(),question.getOption3(),question.getOption4()));
        }
        return questionFromDB;
    }

    public int submitQuiz(int id, List<SubmitResponse> responses) {
        Quiz quiz = quizRepository.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int i = 0;
        int right = 0;
        for(SubmitResponse response : responses) {
            if(response.getSelectedOption().equals(questions.get(i).getRightAnswer())) {
                right++;
            }
            i++;
        }
        return right;
    }
}
