package com.nucleusteq.assessmentPlatform.service.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nucleusteq.assessmentPlatform.dto.CategoryDto;
import com.nucleusteq.assessmentPlatform.entity.Category;
import com.nucleusteq.assessmentPlatform.entity.Quiz;
import com.nucleusteq.assessmentPlatform.exception.AlreadyExistsException;
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
     *
     * @param categoryDto The DTO containing category information.
     * @return A message indicating the result of the operation.
     */
    @Override
    public final String addCategory(final CategoryDto categoryDto) {

        Category newCategory = dtoToEntity(categoryDto);
        Optional<Category> existingCategory = categoryRepository
                .findByCategoryName(newCategory.getCategoryName());
        if (existingCategory.isPresent()) {
            LOGGER.error("Category already exists");
            throw new AlreadyExistsException("This category Already Exist.");
        }
        categoryRepository.save(newCategory);
        return categoryDto.getCategoryName() + " Added Successfully";
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
            return entityToDTO(category);
        } else {
            LOGGER.error("Category not found for id {}"+id);
            throw new ResourceNotFoundException(
                    "Category not found for id: " + id);
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
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Updates a category.
     *
     * @param categoryDto The DTO containing updated category information.
     * @return The updated CategoryDto.
     */
    @Override
    public final String updateCategory(final CategoryDto categoryDto) {

        Category existingCategory = categoryRepository
                .findById(categoryDto.getCategoryId())
                .orElseThrow(() -> {
                    LOGGER.error("Category not found with ID: {}", categoryDto.getCategoryId());
                    return new ResourceNotFoundException("No category found with ID: " + categoryDto.getCategoryId());
                });
        if (!existingCategory.getCategoryName().equals(categoryDto.getCategoryName())
                && categoryRepository
                        .findByCategoryName(categoryDto.getCategoryName())
                        .isPresent()) {
            LOGGER.error("This category Already Exist.");
            throw new AlreadyExistsException("This category Already Exist.");
        }
        existingCategory= dtoToEntity(categoryDto);
        categoryRepository.save(existingCategory);
        return categoryDto.getCategoryName() + " Updated Successfully";
    }

    /**
     * Deletes a category.
     *
     * @param categoryId The ID of the category to delete.
     * @return A message indicating the result of the operation.
     */
    @Override
    public final String deleteCategory(final int categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> {
            LOGGER.error("Category not found with ID: {}", categoryId);
            return new ResourceNotFoundException("No category found with ID: " + categoryId);
        });

        categoryRepository.delete(category);
        return "Category Deletd Successfully";
    }

    /**
     * Gets all quiz By categoryId.
     *
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
     * @param RegistrationDto The UserDTO object.
     * @return Registration object.
     */
    final Category dtoToEntity(final CategoryDto categoryDto) {
        return this.modelMapper.map(categoryDto, Category.class);
    }

    /**
     * Converts User entity to UserDTO.
     * @param Registration User object.
     * @return RegistrationDto object.
     */
    final CategoryDto entityToDTO(final Category category) {
        return this.modelMapper.map(category, CategoryDto.class);
    }

}
