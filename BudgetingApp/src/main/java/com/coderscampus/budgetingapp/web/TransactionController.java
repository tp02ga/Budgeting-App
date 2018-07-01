package com.coderscampus.budgetingapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coderscampus.budgetingapp.domain.Budget;
import com.coderscampus.budgetingapp.domain.Transaction;
import com.coderscampus.budgetingapp.service.BudgetService;

@Controller
@RequestMapping(value= {"/budgets/{budgetId}/groups/{groupId}/categories/{categoryId}/transactions",
                        "/budgets/{budgetId}/transactions"})
public class TransactionController
{
  @Autowired
  private BudgetService budgetService;
  
  @PostMapping("")
  public String addTransactionToBudget(@PathVariable Long budgetId, 
                                     @PathVariable(required=false) Long groupId,
                                     @PathVariable(required=false) Long categoryId)
  {
    Transaction tx = new Transaction();
    Budget budget = budgetService.findOne(budgetId);
    
    if (groupId == null)
    {
      // We're adding a transaction directly to the budget with no group / category associated to it
      
    }
    else
    {
      
    }
    return "redirect:/budgets/"+budgetId;
  }
  
}
