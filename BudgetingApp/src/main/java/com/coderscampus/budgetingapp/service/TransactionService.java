package com.coderscampus.budgetingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.budgetingapp.domain.Transaction;
import com.coderscampus.budgetingapp.repositories.TransactionRepository;

@Service
public class TransactionService
{
  @Autowired
  private TransactionRepository transactionRepo;
  
  public Transaction save (Transaction transaction)
  {
    return transactionRepo.save(transaction);
  }

  public Transaction findOne(Long transactionId)
  {
    return transactionRepo.findOne(transactionId);
  }
}
