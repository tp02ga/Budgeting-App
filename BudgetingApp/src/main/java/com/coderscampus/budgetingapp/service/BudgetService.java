package com.coderscampus.budgetingapp.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import com.coderscampus.budgetingapp.domain.Budget;
import com.coderscampus.budgetingapp.domain.Group;
import com.coderscampus.budgetingapp.domain.User;
import com.coderscampus.budgetingapp.repositories.BudgetRepository;

@Service
public class BudgetService
{
  @Autowired
  private BudgetRepository budgetRepo;
  
  public TreeSet<Budget> getBudgets (@AuthenticationPrincipal User user) 
  {
    Set<User> users = new HashSet<>();
    users.add(user);
    
    return budgetRepo.findByUsers(users);
  }
  
  public Budget saveBudget (User user, Budget budget)
  {
    Set<User> users = new HashSet<>();
    Set<Budget> budgets = new HashSet<>();
    
    users.add(user);
    
    budgets.add(budget);
    
    long count = getBudgetCount(users);
    
    budget.setName("New Budget #" + ++count);
    budget.setUsers(users);
    
    Group group = new Group();
    
    group.setBudget(budget);
    group.setName("Savings");
    
    budget.getGroups().add(group);
    
    user.setBudgets(budgets);
    
    return budgetRepo.save(budget);
  }

  private long getBudgetCount(Set<User> users)
  {
    return budgetRepo.countByUsers(users);
  }

  public Budget findOne(Long budgetId)
  {
    return budgetRepo.findOne(budgetId);
  }
  
  public LocalDate convertStringToDate(String date) throws ParseException
  {
    return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
  }
}
