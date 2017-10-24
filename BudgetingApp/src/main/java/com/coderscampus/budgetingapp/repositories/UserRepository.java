package com.coderscampus.budgetingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.budgetingapp.domain.User;

public interface UserRepository extends JpaRepository<User, Long>
{
  User findByUsername (String username);
}
