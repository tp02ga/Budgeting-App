package com.coderscampus.budgetingapp.repositories;

import java.util.Set;
import java.util.TreeSet;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import com.coderscampus.budgetingapp.domain.Budget;
import com.coderscampus.budgetingapp.domain.User;

public interface BudgetRepository extends JpaRepository<Budget, Long>
{
  @EntityGraph(value = "Budget.users", type = EntityGraphType.LOAD)
  TreeSet<Budget> findByUsers(Set<User> users);
}
