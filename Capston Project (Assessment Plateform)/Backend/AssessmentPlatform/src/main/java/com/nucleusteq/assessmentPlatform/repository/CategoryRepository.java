package com.nucleusteq.assessmentPlatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nucleusteq.assessmentPlatform.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
