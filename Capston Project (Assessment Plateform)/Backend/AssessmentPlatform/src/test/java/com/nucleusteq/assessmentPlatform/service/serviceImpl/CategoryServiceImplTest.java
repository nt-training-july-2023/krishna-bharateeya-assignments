package com.nucleusteq.assessmentPlatform.service.serviceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.nucleusteq.assessmentPlatform.dto.CategoryDto;
import com.nucleusteq.assessmentPlatform.entity.Category;
import com.nucleusteq.assessmentPlatform.exception.ResourceNotFoundException;
import com.nucleusteq.assessmentPlatform.repository.CategoryRepository;

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

        String resultMessage = categoryService.addCategory(categoryDto);
        assertEquals("Test Category Added Successfully", resultMessage);
    }

    @Test
    public void testAddCategory_NullCategoryDto() {
        String resultMessage = categoryService.addCategory(null);
        assertEquals("Please Enter all the field", resultMessage);
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

//    @Test
//    public void testUpdateCategory_Success() {
//        CategoryDto categoryDto = new CategoryDto();
//        categoryDto.setCategoryId(1);
//        categoryDto.setCategoryName("Updated Category");
//
//        Category existingCategory = new Category();
//        existingCategory.setCategoryId(1);
//        existingCategory.setCategoryName("Original Category");
//
//        Category updatedCategory = new Category();
//        updatedCategory.setCategoryId(1);
//        updatedCategory.setCategoryName("Updated Category");
//
//        when(categoryRepository.findById(1)).thenReturn(Optional.of(existingCategory));
//        when(modelMapper.map(categoryDto, Category.class)).thenReturn(updatedCategory);
//        when(categoryRepository.save(updatedCategory)).thenReturn(updatedCategory);
//
//        CategoryDto resultDto = categoryService.updateCategory(categoryDto);
//        assertEquals(categoryDto, resultDto);
//    }

//    @Test
//    public void testUpdateCategory_CategoryNotFound() {
//        CategoryDto categoryDto = new CategoryDto();
//        categoryDto.setCategoryId(100);
//
//        when(categoryRepository.findById(1)).thenReturn(Optional.empty());
//
//        assertThrows(ResourceNotFoundException.class, () -> categoryService.updateCategory(categoryDto));
//    }

    @Test
    public void testDeleteCategory() {
        int categoryId = 1;

        Category category = new Category();
        category.setCategoryId(categoryId);

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        String resultMessage = categoryService.deleteCategory(categoryId);
        assertEquals("Category Deletd Successfully", resultMessage);
    }

    // Add more test cases as needed...
}
