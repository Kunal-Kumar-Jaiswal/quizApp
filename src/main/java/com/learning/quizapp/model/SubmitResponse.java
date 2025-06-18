package com.learning.quizapp.model;

import lombok.Data;

@Data
public class SubmitResponse {
    private int questionId;
    private String selectedOption;
}
