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
import com.nucleusteq.assessmentPlatform.utility.CategoryLoggerMessages;
import com.nucleusteq.assessmentPlatform.utility.SuccessResponse;

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
    public final ResponseEntity<SuccessResponse> saveCategory(
            @Valid @RequestBody final CategoryDto categoryDto) {
        LOGGER.info(CategoryLoggerMessages.SAVE_CATEGORY_REQUEST);
        SuccessResponse response = categoryService.addCategory(categoryDto);
        LOGGER.info(CategoryLoggerMessages.CATEGORY_CREATED_SUCCESSFULLY);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Retrieves a list of all categories.
     * @return A list of CategoryDto objects representing all categories.
     */
    @GetMapping
    public final ResponseEntity<List<CategoryDto>> getAllCategories() {
        LOGGER.info(CategoryLoggerMessages.GET_ALL_CATEGORIES_REQUEST);
        List<CategoryDto> categoryes = categoryService.getAllCategory();
        LOGGER.info(
                CategoryLoggerMessages.ALL_CATEGORIES_RETRIEVED_SUCCESSFULLY);
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
    LOGGER.info(
      CategoryLoggerMessages.GET_ALL_QUIZZES_BY_CATEGORY_REQUEST + categoryId);
        List<Quiz> quiz = categoryService.getAllQuizByCategory(categoryId);
        LOGGER.info(
                CategoryLoggerMessages.ALL_CATEGORIES_RETRIEVED_SUCCESSFULLY);
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
        LOGGER.info(CategoryLoggerMessages.GET_CATEGORY_BY_ID_REQUEST + id);
        CategoryDto category = categoryService.getCategoryById(id);
        LOGGER.info(CategoryLoggerMessages.CATEGORY_RETRIEVED_SUCCESSFULLY);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    /**
     * Updates a category.
     * @param categoryId The ID of the category to update.
     * @param category   The updated CategoryDto object.
     * @return The updated CategoryDto object.
     */
    @PutMapping("update/{categoryId}")
    public final ResponseEntity<SuccessResponse> updateCategory(
            @PathVariable final int categoryId,
            @Valid @RequestBody final CategoryDto category) {
        LOGGER.info(
                CategoryLoggerMessages.UPDATE_CATEGORY_REQUEST + categoryId);
        category.setCategoryId(categoryId);
        SuccessResponse response = categoryService.updateCategory(category);
        LOGGER.info(CategoryLoggerMessages.CATEGORY_UPDATED_SUCCESSFULLY);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Deletes a category by its ID.
     * @param categoryId The ID of the category to delete.
     * @return A message indicating the result of the category deletion.
     */
    @DeleteMapping("delete/{categoryId}")
    public final ResponseEntity<SuccessResponse> deleteCategory(
            @PathVariable final int categoryId) {
        LOGGER.info(
                CategoryLoggerMessages.DELETE_CATEGORY_REQUEST + categoryId);
        SuccessResponse response = categoryService.deleteCategory(categoryId);
        LOGGER.info(CategoryLoggerMessages.CATEGORY_DELETED_SUCCESSFULLY);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
