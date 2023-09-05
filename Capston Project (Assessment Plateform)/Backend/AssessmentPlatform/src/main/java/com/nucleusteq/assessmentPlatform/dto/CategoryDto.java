package com.nucleusteq.assessmentPlatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) class representing a category.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
}
