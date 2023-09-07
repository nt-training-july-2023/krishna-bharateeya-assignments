package com.nucleusteq.assessmentPlatform.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.nucleusteq.assessmentPlatform.entity.Category;

class QuizDTOTest {

    @Mock
    private Category category;
    
    @Mock
    private QuizDTO quizDTO;

    @BeforeEach
    void init() {
        quizDTO = new QuizDTO();
    }

    @Test
    public void testSettierAndGetter() {
        
        assertEquals(0, quizDTO.getQuizId());
        assertEquals(null, quizDTO.getQuizName());
        assertEquals(null, quizDTO.getQuizDescription());
        assertEquals(0, quizDTO.getTimeInMinutes());
    }
    
    @Test
    public void testSettingAndGettingProperties() {
        quizDTO.setQuizId(0);
        quizDTO.setQuizName("Sample Quiz");
        quizDTO.setQuizDescription("this is description");
        quizDTO.setTimeInMinutes(60);
        
        CategoryDto category = new CategoryDto();
        category.setCategoryId(3024);
        category.setCategoryName("cat1");
        category.setDescription("cat1 desc");
        
        quizDTO.setCategory(category);

        assertEquals(0, quizDTO.getQuizId());
        assertEquals("Sample Quiz", quizDTO.getQuizName());
        assertEquals("this is description", quizDTO.getQuizDescription());
        assertEquals(60, quizDTO.getTimeInMinutes());
        assertEquals(category.getCategoryName(), quizDTO.getCategory().getCategoryName());
    }
    @Test
    void allArgConstructor() {
        
        CategoryDto category = new CategoryDto();
        category.setCategoryId(3024);
        category.setCategoryName("cat2");
        category.setDescription("cat2 desc");
        
        QuizDTO allParaQuizDTO =new QuizDTO(
                104,
                "Demo QuizDTO", "dto description", 12,category
                );
        
        assertEquals(104,allParaQuizDTO.getQuizId());
        assertEquals("Demo QuizDTO", allParaQuizDTO.getQuizName());
        assertEquals("dto description", allParaQuizDTO.getQuizDescription());
        assertEquals(12, allParaQuizDTO.getTimeInMinutes());
        assertEquals(category.getCategoryName(), allParaQuizDTO.getCategory().getCategoryName());
    }

}
