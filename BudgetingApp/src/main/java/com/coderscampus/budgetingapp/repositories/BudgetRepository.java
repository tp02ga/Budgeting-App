package com.coderscampus.budgetingapp.repositories;

import java.util.Set;
import java.util.TreeSet;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.budgetingapp.domain.Budget;
import com.coderscampus.budgetingapp.domain.User;

public interface BudgetRepository extends JpaRepository<Budget, Long>
{
  TreeSet<Budget> findByUsers(Set<User> users);
  
  long countByUsers(Set<User> users);
}
