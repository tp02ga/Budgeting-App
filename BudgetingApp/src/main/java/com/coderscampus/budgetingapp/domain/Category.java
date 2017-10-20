package com.coderscampus.budgetingapp.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Category
{
  private Long id;
  private BigDecimal budget;
  private String name;
  private Date startDate;
  private Date endDate;
  
  public Long getId()
  {
    return id;
  }
  public void setId(Long id)
  {
    this.id = id;
  }
  public BigDecimal getBudget()
  {
    return budget;
  }
  public void setBudget(BigDecimal budget)
  {
    this.budget = budget;
  }
  public String getName()
  {
    return name;
  }
  public void setName(String name)
  {
    this.name = name;
  }
  public Date getStartDate()
  {
    return startDate;
  }
  public void setStartDate(Date startDate)
  {
    this.startDate = startDate;
  }
  public Date getEndDate()
  {
    return endDate;
  }
  public void setEndDate(Date endDate)
  {
    this.endDate = endDate;
  }
}
