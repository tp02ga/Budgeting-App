package com.coderscampus.budgetingapp.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coderscampus.budgetingapp.domain.Budget;
import com.coderscampus.budgetingapp.domain.Category;
import com.coderscampus.budgetingapp.domain.Transaction;
import com.coderscampus.budgetingapp.service.BudgetService;
import com.coderscampus.budgetingapp.service.CategoryService;
import com.coderscampus.budgetingapp.service.TransactionService;

@Controller
@RequestMapping(value= {"/budgets/{budgetId}/groups/{groupId}/categories/{categoryId}/transactions",
                        "/budgets/{budgetId}/transactions"})
public class TransactionController
{
  @Autowired
  private BudgetService budgetService;
  @Autowired
  private CategoryService categoryService;
  @Autowired
  private TransactionService transactionService;
  
  @PostMapping("")
  public String addTransactionToBudget(@PathVariable Long budgetId, 
                                     @PathVariable(required=false) Long groupId,
                                     @PathVariable(required=false) Long categoryId)
  {
    Transaction tx = new Transaction();
    Budget budget = budgetService.findOne(budgetId);
    
    tx.setBudget(budget);
    budget.getTransactions().add(tx);
    
    tx.setDate(new Date());
    
    if (categoryId != null)
    {
      Category category = categoryService.findOne(categoryId);
      
      tx.setCategory(category);
      category.getTransactions().add(tx);
    }
    
    tx = transactionService.save(tx);
    return "redirect:/budgets/"+budgetId+"/transactions/"+tx.getId();
  }
  
  @GetMapping("{transactionId}")
  public String getTransaction(@PathVariable Long transactionId, ModelMap model) 
  {
    Transaction transaction = transactionService.findOne(transactionId);
    model.put("transaction", transaction);
    model.put("budget", transaction.getBudget());
    
    return "transaction";
  }
  
  @PostMapping("{transactionId}")
  public String postTransaction(@ModelAttribute Transaction transaction, @PathVariable Long transactionId)
  {
    transaction = transactionService.save(transaction);
    return "redirect:/budgets/"+transaction.getBudget().getId();
  }
  
}
