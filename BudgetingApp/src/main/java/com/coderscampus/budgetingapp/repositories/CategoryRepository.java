package com.coderscampus.budgetingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.budgetingapp.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>
{

}
