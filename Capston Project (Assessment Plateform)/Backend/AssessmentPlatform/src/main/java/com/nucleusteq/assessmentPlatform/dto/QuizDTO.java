package com.nucleusteq.assessmentPlatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuizDTO {
    private int quizId;
    private String quizName;
    private String quizDescription;
    private int timeInMinutes;
    private CategoryDto category;

   
}
