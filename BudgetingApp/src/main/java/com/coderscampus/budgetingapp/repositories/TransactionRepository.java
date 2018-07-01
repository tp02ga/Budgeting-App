package com.coderscampus.budgetingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.budgetingapp.domain.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>
{

}
