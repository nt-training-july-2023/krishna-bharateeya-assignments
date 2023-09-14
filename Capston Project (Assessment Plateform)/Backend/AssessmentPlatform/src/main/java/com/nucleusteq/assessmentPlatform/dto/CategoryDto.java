package com.nucleusteq.assessmentPlatform.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) class representing a category.
 */
@Setter
@Getter
@NoArgsConstructor
public class CategoryDto {

    /**
     * The ID of the category.
     */
    private int categoryId;

    /**
     * The name of the category.
     */
    private String categoryName;

    /**
     * The description of the category.
     */
    private String description;

    /**
     * Copy constructor for the CategoryDto class.
     *
     * @param category The CategoryDto object to create a copy from.
     */
//    public CategoryDto(final CategoryDto category) {
//        this.categoryId = category.categoryId;
//        this.categoryName = category.categoryName;
//        this.description = category.description;
//    }


    /**
     * Copy constructor for the CategoryDto class.
     *@param cId The CategoryDto object to create a copy from.
     * @param cName The CategoryDto object to create a copy from.
     *@param cDescription The CategoryDto object to create a copy from.
     */
    public CategoryDto(final int cId, final String cName,
            final String cDescription) {
        super();
        this.categoryId = cId;
        this.categoryName = cName;
        this.description = cDescription;
    }
}
