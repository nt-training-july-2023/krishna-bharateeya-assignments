package com.nucleusteq.assessmentPlatform.service.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nucleusteq.assessmentPlatform.dto.CategoryDto;
import com.nucleusteq.assessmentPlatform.dto.QuizDTO;
import com.nucleusteq.assessmentPlatform.entity.Category;
import com.nucleusteq.assessmentPlatform.entity.Quiz;
import com.nucleusteq.assessmentPlatform.exception.ResourceNotFoundException;
import com.nucleusteq.assessmentPlatform.repository.CategoryRepository;
import com.nucleusteq.assessmentPlatform.repository.QuizRepository;
import com.nucleusteq.assessmentPlatform.service.CategoryService;

/**
 * Implementation of the CategoryService interface for managing categories.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    /**
     * This is Category Repository object that is for calling. the repository
     * methods.
     */
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private QuizRepository quizRepository;
    /**
     * This is use to map the category with Dto and viceversa..
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Adds a new category.
     *
     * @param categoryDto The DTO containing category information.
     * @return A message indicating the result of the operation.
     */
    @Override
    public final String addCategory(final CategoryDto categoryDto) {

        if (categoryDto != null) {
            Category newCategory = modelMapper.map(categoryDto, Category.class);

            categoryRepository.save(newCategory);
            return categoryDto.getCategoryName() + " Added Successfully";

        }
        return "Please Enter all the field";
    }

    /**
     * Retrieves a category by ID.
     *
     * @param id The ID of the category to retrieve.
     * @return The CategoryDto representing the category.
     * @throws RuntimeException If the category is not found.
     */
    @Override
    public final CategoryDto getCategoryById(final int id) {

        Optional<Category> foundCategory = categoryRepository.findById(id);

        if (foundCategory.isPresent()) {
            Category category = foundCategory.get();
            return modelMapper.map(category, CategoryDto.class);
        } else {
            throw new RuntimeException("Category not found for id: " + id);
        }

    }

    /**
     * Retrieves a list of all categories.
     *
     * @return A list of CategoryDto objects representing categories.
     */
    @Override
    public final List<CategoryDto> getAllCategory() {

        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Updates a category.
     *
     * @param categoryDto The DTO containing updated category information.
     * @return The updated CategoryDto.
     */
    @Override
    public final CategoryDto updateCategory(final CategoryDto categoryDto) {

        Category existingCategory = categoryRepository
                .findById(categoryDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No category found with ID : "
                                + categoryDto.getCategoryId()));

        modelMapper.map(categoryDto, existingCategory);
        existingCategory = categoryRepository.save(existingCategory);
        return modelMapper.map(existingCategory, CategoryDto.class);
    }

    /**
     * Deletes a category.
     *
     * @param categoryId The ID of the category to delete.
     * @return A message indicating the result of the operation.
     */
    @Override
    public final String deleteCategory(final int categoryId) {
        Category category = null;

        category = categoryRepository.findById(categoryId).orElse(null);

        categoryRepository.delete(category);
        return "Category Deletd Successfully";
    }

    @Override
    public List<Quiz> getAllQuizByCategory(int categoryId) {
        Category category = new Category();
        category.setCategoryId(categoryId);
        return quizRepository.findByCategory(category);
    }

}
