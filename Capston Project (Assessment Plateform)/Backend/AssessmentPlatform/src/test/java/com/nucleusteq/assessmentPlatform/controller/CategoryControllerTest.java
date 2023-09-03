package com.nucleusteq.assessmentPlatform.controller;
import com.nucleusteq.assessmentPlatform.dto.CategoryDto;
import com.nucleusteq.assessmentPlatform.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    public void testGetAllCategories() throws Exception {
        List<CategoryDto> categoryDtoList = new ArrayList<>();

        when(categoryService.getAllCategory()).thenReturn(categoryDtoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/category"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(categoryDtoList.size()));

        verify(categoryService, times(1)).getAllCategory();
    }

    @Test
    public void testSaveCategory() throws Exception {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryName("Test Category");
        categoryDto.setDescription("Test description");


        String expectedMessage = "Test Category Added Successfully"; // Set the expected message
        when(categoryService.addCategory(categoryDto)).thenReturn(expectedMessage);

        mockMvc.perform(MockMvcRequestBuilders.post("/category/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"categoryName\":\"Test Category\",\"description\":\"Test description\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void testUpdateCategory_Success() throws Exception {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(1);
        categoryDto.setCategoryName("Updated Category");

        when(categoryService.updateCategory(categoryDto)).thenReturn(categoryDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/category/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"categoryId\":1,\"categoryName\":\"Updated Category\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        }

    @Test
    public void testUpdateCategory_NotFound() throws Exception {
       
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(1);
        categoryDto.setCategoryName("Updated Category");

        when(categoryService.updateCategory(categoryDto)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.put("/category/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"categoryId\":1,\"categoryName\":\"Updated Category\"}"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }
    
    @Test
    public void testGetCategoryById() {
        
        int categoryId = 3020; 
        CategoryDto expectedCategory = new CategoryDto();
        expectedCategory.setCategoryId(categoryId);
        expectedCategory.setCategoryName("krishna");

        when(categoryService.getCategoryById(categoryId)).thenReturn(expectedCategory);
    
        CategoryDto resultCategory = categoryController.getCategoryById(categoryId);
       
        assertEquals(expectedCategory, resultCategory);
    }

}
