package com.nucleusteq.assessmentPlatform.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.nucleusteq.assessmentPlatform.dto.CategoryDto;
import com.nucleusteq.assessmentPlatform.entity.Quiz;

/**
 * Service interface for managing categories.
 */
@Service
public interface CategoryService {

    /**
     * Adds a new category.
     *
     * @param categoryDto The DTO containing category information.
     * @return A message indicating the result of the operation.
     */
    String addCategory(CategoryDto categoryDto);

    /**
     * Retrieves a category by its ID.
     *
     * @param id The ID of the category to retrieve.
     * @return The retrieved CategoryDto.
     */
    CategoryDto getCategoryById(int id);

    /**
     * Retrieves all categories.
     *
     * @return A list of all CategoryDtos.
     */
    List<CategoryDto> getAllCategory();

    /**
     * Deletes a category.
     *
     * @param categoryId The ID of the category to delete.
     * @return A message indicating the result of the operation.
     */
    String deleteCategory(int categoryId);

    /**
     * Updates a category.
     *
     * @param categoryDto The updated category information.
     * @return The updated CategoryDto.
     */
    String updateCategory(CategoryDto categoryDto);

    
    List<Quiz> getAllQuizByCategory(int categoryId);
    
}
