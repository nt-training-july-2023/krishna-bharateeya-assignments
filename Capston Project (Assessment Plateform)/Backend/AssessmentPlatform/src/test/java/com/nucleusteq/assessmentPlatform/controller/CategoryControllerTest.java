package com.nucleusteq.assessmentPlatform.controller;
import com.nucleusteq.assessmentPlatform.dto.CategoryDto;
import com.nucleusteq.assessmentPlatform.entity.Category;
import com.nucleusteq.assessmentPlatform.entity.Quiz;
import com.nucleusteq.assessmentPlatform.service.CategoryService;
import com.nucleusteq.assessmentPlatform.utility.SuccessResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveCategory() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryName("Test Category");
        categoryDto.setDescription("Test description");
 
        SuccessResponse exceptedResponse = new SuccessResponse(HttpStatus.CREATED.value(),"Category created successfully.");

        when(categoryService.addCategory(categoryDto)).thenReturn(exceptedResponse);

        ResponseEntity<SuccessResponse> response = categoryController.saveCategory(categoryDto);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(exceptedResponse, response.getBody());
    }



    @Test
    public void testUpdateCategory_Success() throws Exception {
        int categoryId = 1;
        CategoryDto updatedCategoryDto = new CategoryDto();
        updatedCategoryDto.setCategoryId(categoryId);
        updatedCategoryDto.setCategoryName("Updated Category");
        updatedCategoryDto.setDescription("Updated Description");

        SuccessResponse exceptedResponse = new SuccessResponse(HttpStatus.OK.value(),"Category updated successfully.");

        when(categoryService.updateCategory(updatedCategoryDto)).thenReturn(exceptedResponse);

        ResponseEntity<SuccessResponse> response = categoryController.updateCategory(categoryId, updatedCategoryDto);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(exceptedResponse, response.getBody());
    }

    @Test
    public void testGetCategoryById() {
        
        int categoryId = 3020;
        CategoryDto expectedCategory = new CategoryDto();
        expectedCategory.setCategoryId(categoryId);
        expectedCategory.setCategoryName("krishna");

        when(categoryService.getCategoryById(categoryId)).thenReturn(expectedCategory);

        ResponseEntity<CategoryDto> resultCategory = categoryController.getCategoryById(categoryId);
        assertEquals(HttpStatus.OK, resultCategory.getStatusCode());
        assertEquals(expectedCategory, resultCategory.getBody());
    }

    @Test
    public void testGetAllQuizByCategory() {
        
        int categoryId=1;
        Category category = new Category();
        category.setCategoryId(categoryId);
        category.setCategoryName("Test Category");

        List<Quiz> expectedQuizList = new ArrayList<>();
        Quiz quiz1 = new Quiz(1, "Quiz 1", "Description 1", 10, category);
        Quiz quiz2 = new Quiz(2, "Quiz 2", "Description 2", 20, category);
        expectedQuizList.add(quiz1);
        expectedQuizList.add(quiz2);

        when(categoryService.getAllQuizByCategory(categoryId)).thenReturn(expectedQuizList);

        ResponseEntity<List<Quiz>> responseEntity = categoryController.getAllQuizByCategory(categoryId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedQuizList, responseEntity.getBody());
    }
    
    @Test
    public void testDeleteCategory() {
        int categoryId=1;
        
        SuccessResponse successResponse = new SuccessResponse(HttpStatus.CREATED.value(),"Category deleted successfully.");
        

        when(categoryService.deleteCategory(categoryId)).thenReturn(successResponse);

        ResponseEntity<SuccessResponse> response = categoryController.deleteCategory(categoryId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(successResponse, response.getBody());
        
    }
    
    @Test
    public void testGetAllCategories() throws Exception {
        List<CategoryDto> categoryDtoList = new ArrayList<>();

        when(categoryService.getAllCategory()).thenReturn(categoryDtoList);
        ResponseEntity<List<CategoryDto>> responseEntity = categoryController.getAllCategories();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
