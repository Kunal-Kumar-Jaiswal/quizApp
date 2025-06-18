package com.learning.quizapp.repository;

import com.learning.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {
    List<Question> findByCategory(String category);

    @Query(value = "select * from question where category=:category order by rand() limit :totalQuestions",nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int totalQuestions);
}
