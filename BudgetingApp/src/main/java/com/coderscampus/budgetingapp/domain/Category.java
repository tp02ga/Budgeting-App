package com.coderscampus.budgetingapp.domain;

import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

@Entity
public class Category
{
  private Long id;
  private BigDecimal budget;
  private String name;
  private Group group;
  private Set<Transaction> transactions = new TreeSet<>();
  
  @Id @GeneratedValue
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
  
  @ManyToOne
  public Group getGroup()
  {
    return group;
  }
  public void setGroup(Group group)
  {
    this.group = group;
  }
  @OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY, mappedBy="category")
  @OrderBy("date DESC")
  public Set<Transaction> getTransactions()
  {
    return transactions;
  }
  public void setTransactions(Set<Transaction> transactions)
  {
    this.transactions = transactions;
  }
  
  @Transient
  public BigDecimal getSpent() {
    double sum = this.getTransactions().stream()
                          .mapToDouble(t -> {
                            if (t.getTotal() == null)
                              return 0.0;
                            else 
                              return t.getTotal().doubleValue(); 
                          })
                          .sum();
    return BigDecimal.valueOf(sum);
  }
  @Transient
  public BigDecimal getRemaining() {
    return this.getBudget().subtract(this.getSpent());
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
    Category other = (Category) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
}
