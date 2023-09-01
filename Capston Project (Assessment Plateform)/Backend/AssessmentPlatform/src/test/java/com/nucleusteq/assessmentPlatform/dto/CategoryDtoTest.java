package com.nucleusteq.assessmentPlatform.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class categoryDtoDtoTest {

    CategoryDto categoryDto;
    @BeforeEach
    void init() {
        categoryDto = new CategoryDto();
    }
    @Test
    void testGettersAndSetters() {
        assertEquals(0,categoryDto.getCategoryId());
        assertEquals(null, categoryDto.getCategoryName());
        assertEquals(null, categoryDto.getDescription());
        
    }
    @Test
    void testDefaultConstructor() {
        CategoryDto categoryDtoObj = new CategoryDto();
        assertEquals(0,categoryDtoObj.getCategoryId());
        assertEquals(null, categoryDtoObj.getCategoryName());
        assertEquals(null, categoryDtoObj.getDescription());
    }
    @Test
    void testParameterisedConstructor() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(3010);
        categoryDto.setCategoryName("Front End");
        categoryDto.setDescription("React");
        
        assertEquals(3010, categoryDto.getCategoryId());
        assertEquals("Front End", categoryDto.getCategoryName());
        assertEquals("React", categoryDto.getDescription());
    }
    
    @Test
    void allArgConstructor() {
        CategoryDto allParaCategoryDto =new CategoryDto(
                103,
                "java",
                "Spring"
                );
        assertEquals(103,allParaCategoryDto.getCategoryId());
        assertEquals("java", allParaCategoryDto.getCategoryName());
        assertEquals("Spring", allParaCategoryDto.getDescription());
    }

}
