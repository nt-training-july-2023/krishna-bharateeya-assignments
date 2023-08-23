package com.nucleusteq.assessmentPlatform.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nucleusteq.assessmentPlatform.entity.Category;
import com.nucleusteq.assessmentPlatform.repository.CategoryRepository;
import com.nucleusteq.assessmentPlatform.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public String addCategory(Category category) {

		if (category != null) {
			Category newCategory = new Category(0, category.getCategoryName(), category.getDescription());

			try {
				categoryRepository.save(newCategory);
				return category.getCategoryName() + " Added Successfully";
			} catch (Exception e) {
				throw e;
			}
		}
		return "Please Enter all the field";
	}

	@Override
	public Category getCategoryById(int id) {

		Optional<Category> foundCategory = categoryRepository.findById(id);

		if (foundCategory.isPresent()) {
			Category category = foundCategory.get();
			return category;
		} else {
			throw new RuntimeException("Category not found for id: " + id);
		}

	}

	@Override
	public List<Category> getAllCategory() {
		return categoryRepository.findAll();
	}
	@Override
	public Category updateCategory(Category category) {

	    Category existingCategory = categoryRepository.findById(category.getCategoryId()).orElse(null);

	    if (existingCategory != null) {

	    	if(category.getCategoryName()!=null)
	        existingCategory.setCategoryName(category.getCategoryName());
	    	
	    	if(category.getDescription()!=null)
	        existingCategory.setDescription(category.getDescription());

	        existingCategory = categoryRepository.save(existingCategory);
	        
	        return existingCategory;
	    } else {
	        
	        return null;
	    }
	}

	@Override
	public void deleteCategory(int categoryId) {
		Category category = null;

		category = categoryRepository.findById(categoryId).orElse(null);

		categoryRepository.delete(category);
	}

}
