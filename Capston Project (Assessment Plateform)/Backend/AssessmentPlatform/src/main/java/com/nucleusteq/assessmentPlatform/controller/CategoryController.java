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

import com.nucleusteq.assessmentPlatform.entity.Category;
import com.nucleusteq.assessmentPlatform.service.CategoryService;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@PostMapping(path = "/save")
	public String saveUser(@RequestBody Category category) {

		return categoryService.addCategory(category);
	}
	
	
    @GetMapping
    public List<Category> getAllCategories() {
        List<Category> categorys = categoryService.getAllCategory();
        return categorys;
    }
    
    @GetMapping("/{id}")
	public Category getCategoryById(@PathVariable int id) {
		return categoryService.getCategoryById(id);
	}
    
    
    
    @PutMapping("update/{categoryId}")
    public Category updateCategory(@PathVariable int categoryId, @RequestBody Category category) {
    	category.setCategoryId(categoryId);
    	return categoryService.updateCategory(category);
    }

    @DeleteMapping("delete/{categoryId}")
    public void deleteCategory(@PathVariable int categoryId) {
        categoryService.deleteCategory(categoryId);
    }
}
