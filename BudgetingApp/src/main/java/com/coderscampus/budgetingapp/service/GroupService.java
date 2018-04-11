package com.coderscampus.budgetingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.budgetingapp.domain.Budget;
import com.coderscampus.budgetingapp.domain.Group;
import com.coderscampus.budgetingapp.repositories.GroupRepository;

@Service
public class GroupService
{
  @Autowired
  private BudgetService budgetService;
  @Autowired
  private GroupRepository groupRepo;
  
  public Group createNewGroup(Long budgetId)
  {
    Group group = new Group();
    
    Budget budget = budgetService.findOne(budgetId);
    group.setBudget(budget);
    budget.getGroups().add(group);
    
    return save(group);
  }

  public Group save(Group group)
  {
    return groupRepo.save(group);
  }

  public Group findOne(Long groupId)
  {
   return groupRepo.findOne(groupId);
  }
}
