package com.nucleusteq.assessmentPlatform.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.nucleusteq.assessmentPlatform.entity.Category;

@Service
public interface CategoryService {

    public String addCategory(Category category);

    public Category getCategoryById(int id);

    public List<Category> getAllCategory();

//	Category updateCategory(Category category);
    void deleteCategory(int categoryId);

    public Category updateCategory(Category category);

}
