package com.coderscampus.budgetingapp.web;

import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coderscampus.budgetingapp.domain.Budget;
import com.coderscampus.budgetingapp.domain.User;
import com.coderscampus.budgetingapp.service.BudgetService;

@Controller
@RequestMapping("/budgets")
public class BudgetController
{
  @Autowired
  private BudgetService budgetService;
  
  @RequestMapping(value="", method=RequestMethod.GET)
  public String getBudgets(@AuthenticationPrincipal User user, ModelMap model)
  {
    // this should fetch budgets from the database for the logged in user
    populateUserBudgetsOnModel(user, model);
    
    return "budgets";
  }

  @GetMapping("{budgetId}")
  public String getBudget(@PathVariable Long budgetId, ModelMap model)
  {
    Budget budget = budgetService.findOne(budgetId);
    
    model.put("budget", budget);
    
    return "budget";
  }
  
  private void populateUserBudgetsOnModel(User user, ModelMap model)
  {
    TreeSet<Budget> budgets = budgetService.getBudgets(user);
    
    model.put("budgets", budgets);
  }

  @PostMapping("")
  public @ResponseBody Budget postBudget(@AuthenticationPrincipal User user, ModelMap model)
  {
    Budget budget = new Budget();
    budget = budgetService.saveBudget(user, budget);
    
    populateUserBudgetsOnModel(user, model);
    
    return budget;
  }
}
