package com.nucleusteq.assessmentPlatform.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import jakarta.validation.Valid;

/**
 * Controller class for managing categories.
 */
@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {

    /**
     * This is category service object it call the category methods.
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * this is logger object that is use to generate log.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(CategoryController.class);

    /**
     * Adds a new category.
     * @param categoryDto The CategoryDto object containing category
     *                    information.
     * @return A message indicating the result of the category addition.
     */
    @PostMapping(path = "/save")
    public final ResponseEntity<String> saveCategory(
            @Valid @RequestBody final CategoryDto categoryDto) {
        LOGGER.info("Received a request to save a new category.");
        String response = categoryService.addCategory(categoryDto);
        LOGGER.info("Category Created Successfully.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Retrieves a list of all categories.
     * @return A list of CategoryDto objects representing all categories.
     */
    @GetMapping
    public final ResponseEntity<List<CategoryDto>> getAllCategories() {
        LOGGER.info("Received a request to get all category.");
        List<CategoryDto> categoryes = categoryService.getAllCategory();
        LOGGER.info("All category Retrived successfully.");
        return new ResponseEntity<>(categoryes, HttpStatus.OK);
    }

    /**
     * Retrieves a category by its ID.
     * @param categoryId The ID of the category to retrieve.
     * @return The CategoryDto object representing the retrieved category.
     */
    @GetMapping("quizzes/{categoryId}")
    public final ResponseEntity<List<Quiz>> getAllQuizByCategory(
            @PathVariable final int categoryId) {
        LOGGER.info("Received a request to get all quiz by Category Id {}"
                + categoryId);
        List<Quiz> quiz = categoryService.getAllQuizByCategory(categoryId);
        LOGGER.info("All Category Retrived Successfully.");
        return new ResponseEntity<>(quiz, HttpStatus.OK);
    }

    /**
     * Retrieves a category by its ID.
     * @param id The ID of the category to retrieve.
     * @return The CategoryDto object representing the retrieved category.
     */
    @GetMapping("/{id}")
    public final ResponseEntity<CategoryDto> getCategoryById(
            @PathVariable final int id) {
        LOGGER.info("Received a request to get Category by Id {}" + id);
        CategoryDto category = categoryService.getCategoryById(id);
        LOGGER.info("Category Retrived Successfully.");
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    /**
     * Updates a category.
     * @param categoryId The ID of the category to update.
     * @param category   The updated CategoryDto object.
     * @return The updated CategoryDto object.
     */
    @PutMapping("update/{categoryId}")
    public final ResponseEntity<String> updateCategory(
            @PathVariable final int categoryId,
            @Valid @RequestBody final CategoryDto category) {
        LOGGER.info(
                "Received a request to update Category for Id {}" + categoryId);
        category.setCategoryId(categoryId);
        String response = categoryService.updateCategory(category);
        LOGGER.info("Category Updated Successfully.");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Deletes a category by its ID.
     * @param categoryId The ID of the category to delete.
     * @return A message indicating the result of the category deletion.
     */
    @DeleteMapping("delete/{categoryId}")
    public final ResponseEntity<String> deleteCategory(
            @PathVariable final int categoryId) {
        LOGGER.info(
                "Received a request to delete Category for Id {}" + categoryId);
        String response = categoryService.deleteCategory(categoryId);
        LOGGER.info("Category Deleted Successfully.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
