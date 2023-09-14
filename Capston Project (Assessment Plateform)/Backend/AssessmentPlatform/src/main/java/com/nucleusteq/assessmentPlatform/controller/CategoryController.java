package com.nucleusteq.assessmentPlatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nucleusteq.assessmentPlatform.dto.CategoryDto;
import com.nucleusteq.assessmentPlatform.entity.Quiz;
import com.nucleusteq.assessmentPlatform.service.CategoryService;

/**
 * Controller class for managing categories.
 */
@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {

    /**
     * This is category service object it call the category methods.
     *
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * Adds a new category.
     *
     * @param categoryDto The CategoryDto object containing category
     *                    information.
     * @return A message indicating the result of the category addition.
     */
    @PostMapping(path = "/save")
    public final String saveUser(@RequestBody final CategoryDto categoryDto) {

        return categoryService.addCategory(categoryDto);
    }

    /**
     * Retrieves a list of all categories.
     *
     * @return A list of CategoryDto objects representing all categories.
     */
    @GetMapping
    public final List<CategoryDto> getAllCategories() {
        List<CategoryDto> categoryes = categoryService.getAllCategory();
        return categoryes;
    }

    /**
     * Retrieves a category by its ID.
     *
     * @param categoryId The ID of the category to retrieve.
     * @return The CategoryDto object representing the retrieved category.
     */
    @GetMapping("quizzes/{categoryId}")
    public final List<Quiz> getAllQuizByCategory(@PathVariable final int categoryId) {
        return categoryService.getAllQuizByCategory(categoryId);
    }

    /**
     * Retrieves a category by its ID.
     *
     * @param id The ID of the category to retrieve.
     * @return The CategoryDto object representing the retrieved category.
     */
    @GetMapping("/{id}")
    public final CategoryDto getCategoryById(@PathVariable final int id) {
        return categoryService.getCategoryById(id);
    }
    /**
     * Updates a category.
     *
     * @param categoryId The ID of the category to update.
     * @param category   The updated CategoryDto object.
     * @return The updated CategoryDto object.
     */
    @PutMapping("update/{categoryId}")
    public final String updateCategory(@PathVariable final int categoryId,
            @RequestBody final CategoryDto category) {
        category.setCategoryId(categoryId);
        return categoryService.updateCategory(category);
    }

    /**
     * Deletes a category by its ID.
     *
     * @param categoryId The ID of the category to delete.
     * @return A message indicating the result of the category deletion.
     */
    @DeleteMapping("delete/{categoryId}")
    public final String deleteCategory(@PathVariable final int categoryId) {
        return categoryService.deleteCategory(categoryId);
    }
}
