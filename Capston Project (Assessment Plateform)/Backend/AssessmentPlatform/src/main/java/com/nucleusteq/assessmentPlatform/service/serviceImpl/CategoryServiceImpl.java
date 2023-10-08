package com.nucleusteq.assessmentPlatform.service.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nucleusteq.assessmentPlatform.dto.CategoryDto;
import com.nucleusteq.assessmentPlatform.entity.Category;
import com.nucleusteq.assessmentPlatform.entity.Quiz;
import com.nucleusteq.assessmentPlatform.exception.DuplicateResourceException;
import com.nucleusteq.assessmentPlatform.exception.ResourceNotFoundException;
import com.nucleusteq.assessmentPlatform.repository.CategoryRepository;
import com.nucleusteq.assessmentPlatform.repository.QuizRepository;
import com.nucleusteq.assessmentPlatform.service.CategoryService;
import com.nucleusteq.assessmentPlatform.utility.Message;
import com.nucleusteq.assessmentPlatform.utility.SuccessResponse;

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

    /**
     * This is use to call the quizRepository functions.
     */
    @Autowired
    private QuizRepository quizRepository;
    /**
     * This is use to map the category with Dto and viceversa..
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * this is logger object that is use to generate log.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(CategoryServiceImpl.class);

    /**
     * Adds a new category.
     * @param categoryDto The DTO containing category information.
     * @return A message indicating the result of the operation.
     */
    @Override
    public final SuccessResponse addCategory(final CategoryDto categoryDto) {

        Category newCategory = dtoToEntity(categoryDto);
        Optional<Category> existingCategory = categoryRepository
                .findByCategoryName(newCategory.getCategoryName());
        if (existingCategory.isPresent()) {
            LOGGER.error(Message.CATEGORY_ALREADY_EXISTS);
            throw new DuplicateResourceException(
                Message.CATEGORY_ALREADY_EXISTS);
        }
        categoryRepository.save(newCategory);
        return new SuccessResponse(HttpStatus.CREATED.value(),
                Message.CATEGORY_CREATED_SUCCESSFULLY);
    }

    /**
     * Retrieves a category by ID.
     * @param id The ID of the category to retrieve.
     * @return The CategoryDto representing the category.
     * @throws RuntimeException If the category is not found.
     */
    @Override
    public final CategoryDto getCategoryById(final int id) {

        Optional<Category> foundCategory = categoryRepository.findById(id);

        if (foundCategory.isPresent()) {
            Category category = foundCategory.get();
            return entityToDTO(category);
        } else {
            LOGGER.error(Message.CATEGORY_NOT_FOUND + id);
            throw new ResourceNotFoundException(
                    Message.CATEGORY_NOT_FOUND + id);
        }

    }

    /**
     * Retrieves a list of all categories.
     * @return A list of CategoryDto objects representing categories.
     */
    @Override
    public final List<CategoryDto> getAllCategory() {

        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Updates a category.
     * @param categoryDto The DTO containing updated category information.
     * @return The updated CategoryDto.
     */
    @Override
    public final SuccessResponse updateCategory(final CategoryDto categoryDto) {

        Category existingCategory = categoryRepository
                .findById(categoryDto.getCategoryId()).orElseThrow(() -> {
                    LOGGER.error(Message.CATEGORY_NOT_FOUND,
                            categoryDto.getCategoryId());
                    return new ResourceNotFoundException(
                            Message.CATEGORY_NOT_FOUND
                                    + categoryDto.getCategoryId());
                });
        if (!existingCategory.getCategoryName()
                .equals(categoryDto.getCategoryName())
                && categoryRepository
                        .findByCategoryName(categoryDto.getCategoryName())
                        .isPresent()) {
            LOGGER.error(Message.CATEGORY_ALREADY_EXISTS);
            throw new DuplicateResourceException(
                Message.CATEGORY_ALREADY_EXISTS);
        }
        existingCategory = dtoToEntity(categoryDto);
        categoryRepository.save(existingCategory);
        return new SuccessResponse(HttpStatus.OK.value(),
                Message.CATEGORY_UPDATED_SUCCESSFULLY);
    }

    /**
     * Deletes a category.
     * @param categoryId The ID of the category to delete.
     * @return A message indicating the result of the operation.
     */
    @Override
    public final SuccessResponse deleteCategory(final int categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> {
                    LOGGER.error(Message.CATEGORY_NOT_FOUND, categoryId);
                    return new ResourceNotFoundException(
                            Message.CATEGORY_NOT_FOUND + categoryId);
                });

        categoryRepository.delete(category);
        return new SuccessResponse(HttpStatus.OK.value(),
                Message.CATEGORY_DELETED_SUCCESSFULLY);
    }

    /**
     * Gets all quiz By categoryId.
     * @param categoryId The ID of the category to get the quiz.
     * @return list of quiz related to this category.
     */
    @Override
    public final List<Quiz> getAllQuizByCategory(final int categoryId) {
        Category category = new Category();
        category.setCategoryId(categoryId);
        return quizRepository.findByCategory(category);
    }

    /**
     * Converts UserDTO to User entity.
     * @param categoryDto The UserDTO object.
     * @return Category object.
     */
    final Category dtoToEntity(final CategoryDto categoryDto) {
        return this.modelMapper.map(categoryDto, Category.class);
    }

    /**
     * Converts User entity to UserDTO.
     * @param category category object.
     * @return CategoryDto object.
     */
    final CategoryDto entityToDTO(final Category category) {
        return this.modelMapper.map(category, CategoryDto.class);
    }

}
