package com.coderscampus.budgetingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.budgetingapp.domain.Category;
import com.coderscampus.budgetingapp.repositories.CategoryRepository;

@Service
public class CategoryService
{
  @Autowired
  private CategoryRepository categoryRepo;
  
  public Category saveCategory (Category category)
  {
    return categoryRepo.save(category);
  }

  public Category findOne(Long categoryId)
  {
    return categoryRepo.findOne(categoryId);
  }
}
