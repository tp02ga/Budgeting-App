package com.coderscampus.budgetingapp.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Budget implements Comparable<Budget>
{
  private Long id;
  private String name;
  private Set<User> users = new HashSet<>();
  private Set<Group> groups = new TreeSet<>();
  
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
  @ManyToMany
  @JoinTable(inverseJoinColumns=@JoinColumn(name="user_id"), joinColumns=@JoinColumn(name="budget_id"))
  @JsonIgnore
  public Set<User> getUsers()
  {
    return users;
  }
  public void setUsers(Set<User> users)
  {
    this.users = users;
  }
  @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="budget")
  @JsonIgnore
  public Set<Group> getGroups()
  {
    return groups;
  }
  public void setGroups(Set<Group> groups)
  {
    this.groups = groups;
  }
  
  @Override
  public int compareTo(Budget budget)
  {
    return this.id.compareTo(budget.getId());
  }
}
