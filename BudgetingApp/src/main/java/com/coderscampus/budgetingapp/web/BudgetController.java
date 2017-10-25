package com.coderscampus.budgetingapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/budgets")
public class BudgetController
{
  @RequestMapping(value="", method=RequestMethod.GET)
  public String getBudget(ModelMap model)
  {
    // this should fetch budgets from the database for the logged in user
    return "budget";
  }
}
