package com.coderscampus.budgetingapp.web;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coderscampus.budgetingapp.domain.Category;
import com.coderscampus.budgetingapp.domain.Group;
import com.coderscampus.budgetingapp.domain.Transaction;
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
    
    LocalDate startDate = category.getGroup().getBudget().getStartDate();
    LocalDate endDate = category.getGroup().getBudget().getEndDate();
    
    Set<Transaction> filteredTxns = category.getTransactions().stream()
                                            .filter(t -> (t.getDate().equals(startDate) 
                                                          || t.getDate().isAfter(startDate)) && 
                                                          (t.getDate().isBefore(endDate) || 
                                                              t.getDate().equals(endDate)))
                                            .collect(Collectors.toSet());
    model.put("filteredTxns", filteredTxns);
    model.put("category", category);
    model.put("group", category.getGroup());
    
    return "category";
  }
  
  @PostMapping("{categoryId}")
  public String saveCategory (@ModelAttribute Category category, @PathVariable Long categoryId)
  {
    Category categoryFromDB = categoryService.findOne(categoryId);
    
    categoryFromDB.setName(category.getName());
    categoryFromDB.setBudget(category.getBudget());
    
    categoryFromDB = categoryService.saveCategory(categoryFromDB);
    
    Long budgetId = categoryFromDB.getGroup().getBudget().getId();
    
    return "redirect:/budgets/"+budgetId;
  }
}
