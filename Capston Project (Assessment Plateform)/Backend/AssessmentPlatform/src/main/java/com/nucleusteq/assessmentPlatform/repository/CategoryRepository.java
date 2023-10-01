package com.nucleusteq.assessmentPlatform.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nucleusteq.assessmentPlatform.entity.Category;

/**
 * Repository interface for managing Category entities.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    /**
     * Retrieves a category by its title.
     * @param name The title of the Category.
     * @return An optional containing the retrieved category object or an
     *         empty optional if not found.
     */
    Optional<Category> findByCategoryName(String name);

}
