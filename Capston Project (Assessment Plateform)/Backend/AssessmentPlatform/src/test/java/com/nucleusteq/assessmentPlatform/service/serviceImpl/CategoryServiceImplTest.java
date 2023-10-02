package com.nucleusteq.assessmentPlatform.service.serviceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;

import com.nucleusteq.assessmentPlatform.dto.CategoryDto;
import com.nucleusteq.assessmentPlatform.entity.Category;
import com.nucleusteq.assessmentPlatform.entity.Quiz;
import com.nucleusteq.assessmentPlatform.exception.AlreadyExistsException;
import com.nucleusteq.assessmentPlatform.exception.ResourceNotFoundException;
import com.nucleusteq.assessmentPlatform.repository.CategoryRepository;
import com.nucleusteq.assessmentPlatform.repository.QuizRepository;
import com.nucleusteq.assessmentPlatform.utility.SuccessResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;
    
    @Mock
    private QuizRepository quizRepository;
    
    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddCategory_Success() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryName("Test Category");

        Category category = new Category();
        category.setCategoryName("Test Category");

        when(modelMapper.map(categoryDto, Category.class)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(category);

        SuccessResponse response = categoryService.addCategory(categoryDto);
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
        assertEquals("Category created successfully.", response.getMessage());

    }

    @Test
    public void testGetCategoryById_Success() {
        int categoryId = 1;

        Category category = new Category();
        category.setCategoryId(categoryId);
        category.setCategoryName("Test Category");

        CategoryDto expectedDto = new CategoryDto();
        expectedDto.setCategoryId(categoryId);
        expectedDto.setCategoryName("Test Category");

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        when(modelMapper.map(category, CategoryDto.class)).thenReturn(expectedDto);

        CategoryDto resultDto = categoryService.getCategoryById(categoryId);
        assertEquals(expectedDto, resultDto);
    }

    @Test
    public void testGetCategoryById_CategoryNotFound() {
        int categoryId = 1;

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> categoryService.getCategoryById(categoryId));
    }

    @Test
    public void testGetAllCategory() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());
        categories.add(new Category());

        when(categoryRepository.findAll()).thenReturn(categories);

        CategoryDto dto1 = new CategoryDto();
        CategoryDto dto2 = new CategoryDto();

        when(modelMapper.map(categories.get(0), CategoryDto.class)).thenReturn(dto1);
        when(modelMapper.map(categories.get(1), CategoryDto.class)).thenReturn(dto2);

        List<CategoryDto> result = categoryService.getAllCategory();
        assertEquals(2, result.size());
        assertEquals(dto1, result.get(0));
        assertEquals(dto2, result.get(1));
    }

    @Test
    public void testUpdateCategory_Success() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(1);
        categoryDto.setCategoryName("Updated Category");

        Category existingCategory = new Category();
        existingCategory.setCategoryId(1);
        existingCategory.setCategoryName("Original Category");

        Category updatedCategory = new Category();
        updatedCategory.setCategoryId(1);
        updatedCategory.setCategoryName("Updated Category");

        when(categoryRepository.findById(1)).thenReturn(Optional.of(existingCategory));
        when(modelMapper.map(categoryDto, Category.class)).thenReturn(updatedCategory);
        when(categoryRepository.save(updatedCategory)).thenReturn(updatedCategory);

        SuccessResponse response = categoryService.updateCategory(categoryDto);
       
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals("Category updated successfully.", response.getMessage());

    }

    @Test
    public void testUpdateCategory_CategoryNotFound() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(100);

        when(categoryRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> categoryService.updateCategory(categoryDto));
    }

    @Test
    public void testDeleteCategory() {
        int categoryId = 1;

        Category category = new Category();
        category.setCategoryId(categoryId);

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        SuccessResponse response = categoryService.deleteCategory(categoryId);
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals("Category deleted successfully.", response.getMessage());

    }

    @Test
    public void testGetAllQuizByCategory() {
        int categoryId = 1;
        List<Quiz> expectedQuizzes = new ArrayList<>();
        expectedQuizzes.add(new Quiz());
        expectedQuizzes.add(new Quiz());
        expectedQuizzes.add(new Quiz());

        when(quizRepository.findByCategory(any(Category.class))).thenReturn(expectedQuizzes);
        List<Quiz> resultQuizzes = categoryService.getAllQuizByCategory(categoryId);
        verify(quizRepository, times(1)).findByCategory(any(Category.class));
        assertEquals(expectedQuizzes, resultQuizzes);
    }
    
    @Test
    public void testAddCategory_CategoryAlreadyExists() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(1);
        categoryDto.setCategoryName("ExistingCategory");
        categoryDto.setDescription("description");

        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryName(categoryDto.getCategoryName());
        category.setDescription(categoryDto.getDescription());
        
        when(categoryRepository.findByCategoryName("ExistingCategory")).thenReturn(Optional.of(category));
        when(modelMapper.map(categoryDto, Category.class)).thenReturn(category);

        assertThrows(AlreadyExistsException.class, () -> {
            categoryService.addCategory(categoryDto);
        });
    }
    
    @Test
    public void testUpdateCategory_CategoryAlreadyExists() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(1);
        categoryDto.setCategoryName("ExistingCategory");
        categoryDto.setDescription("description");

        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryName(categoryDto.getCategoryName());
        category.setDescription(categoryDto.getDescription());
        Category existingCategory = new Category();
        existingCategory.setCategoryId(1);
        existingCategory.setCategoryName("Category Existing");
        existingCategory.setDescription(categoryDto.getDescription());
        when(categoryRepository.findById(1)).thenReturn(Optional.of(existingCategory));
        when(categoryRepository.findByCategoryName("ExistingCategory")).thenReturn(Optional.of(category));
        when(modelMapper.map(categoryDto, Category.class)).thenReturn(category);

        assertThrows(AlreadyExistsException.class, () -> {
            categoryService.updateCategory(categoryDto);
        });
    }
}
