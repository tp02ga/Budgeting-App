package com.coderscampus.budgetingapp.domain;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="groups")
public class Group implements Comparable<Group>
{
  private Long id;
  private String name;
  private Budget budget;
  private Set<Category> categories = new TreeSet<>();
  
  @Id @GeneratedValue
  public Long getId()
  {
    return id;
  }
  public void setId(Long id)
  {
    this.id = id;
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
  public Budget getBudget()
  {
    return budget;
  }
  public void setBudget(Budget budget)
  {
    this.budget = budget;
  }
  
  @OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY, mappedBy="group")
  public Set<Category> getCategories()
  {
    return categories;
  }
  public void setCategories(Set<Category> categories)
  {
    this.categories = categories;
  }
  @Override
  public int compareTo(Group o)
  {
    int compareTo = 0;
    if (this.getId() != null && o.getId() != null)
      compareTo = this.getId().compareTo(o.getId());
    
    return compareTo;
  }
  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }
  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Group other = (Group) obj;
    if (id == null)
    {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
  
  
}
