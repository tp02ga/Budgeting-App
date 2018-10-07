package com.coderscampus.budgetingapp.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Transaction
{
  private Long id;
  private LocalDate date;
  private BigDecimal total;
  private String type;
  private String note;
  private Category category;
  private Budget budget;
  
  @Id @GeneratedValue
  public Long getId()
  {
    return id;
  }
  public void setId(Long id)
  {
    this.id = id;
  }
  @DateTimeFormat(pattern="yyyy-MM-dd")
  public LocalDate getDate()
  {
    return date;
  }
  public void setDate(LocalDate date)
  {
    this.date = date;
  }
  public BigDecimal getTotal()
  {
    return total;
  }
  public void setTotal(BigDecimal total)
  {
    this.total = total;
  }
  public String getType()
  {
    return type;
  }
  public void setType(String type)
  {
    this.type = type;
  }
  public String getNote()
  {
    return note;
  }
  public void setNote(String note)
  {
    this.note = note;
  }
  @ManyToOne
  public Category getCategory()
  {
    return category;
  }
  public void setCategory(Category category)
  {
    this.category = category;
  }
  @ManyToOne
  public Budget getBudget()
  {
    return budget;
  }
  public void setBudget(Budget budget)
  {
    this.budget = budget;
  }
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Transaction other = (Transaction) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
}
