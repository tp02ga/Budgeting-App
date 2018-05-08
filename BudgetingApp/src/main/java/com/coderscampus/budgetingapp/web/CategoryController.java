package com.coderscampus.budgetingapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
  public String postCategory(@PathVariable Long groupId)
  {
    Category category = new Category();
    
    Group group = groupService.findOne(groupId);
    
    category.setGroup(group);
    group.getCategories().add(category);
    category.setName("Test Category");
    
    category = categoryService.saveCategory(category);
    
    return "redirect:/budgets/"+group.getBudget().getId()+"/groups/"+group.getId()+"/categories/"+category.getId();
  }
  
  @GetMapping("{categoryId}")
  public String getCategory(@PathVariable Long categoryId, ModelMap model)
  {
    Category category = categoryService.findOne(categoryId);
    
    model.put("category", category);
    model.put("group", category.getGroup());
    
    return "category";
  }
}
