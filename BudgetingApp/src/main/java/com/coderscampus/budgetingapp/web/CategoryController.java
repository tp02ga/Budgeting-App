package com.coderscampus.budgetingapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coderscampus.budgetingapp.domain.Category;
import com.coderscampus.budgetingapp.domain.Group;
import com.coderscampus.budgetingapp.service.CategoryService;
import com.coderscampus.budgetingapp.service.GroupService;

@Controller
@RequestMapping("/budgets/{budgetId}/groups/{groupId}/categories")
public class CategoryController
{
  @Autowired
  private GroupService groupService;
  @Autowired
  private CategoryService categoryService;
  
  @PostMapping("")
  public @ResponseBody Category postCategory(@PathVariable Long groupId)
  {
    Category category = new Category();
    
    Group group = groupService.findOne(groupId);
    
    category.setGroup(group);
    group.getCategories().add(category);
    category.setName("Test Category");
    
    return categoryService.saveCategory(category);
  }
}
