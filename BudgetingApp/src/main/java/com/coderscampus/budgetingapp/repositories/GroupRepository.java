package com.coderscampus.budgetingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.budgetingapp.domain.Group;

public interface GroupRepository extends JpaRepository<Group, Long>
{

}
