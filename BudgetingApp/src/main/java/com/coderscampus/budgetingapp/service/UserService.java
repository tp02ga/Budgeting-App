package com.coderscampus.budgetingapp.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.budgetingapp.domain.Authority;
import com.coderscampus.budgetingapp.domain.User;
import com.coderscampus.budgetingapp.repositories.UserRepository;

@Service
public class UserService
{
  @Autowired
  private UserRepository userRepo;
  
  public User saveUser(User user)
  {
    Authority authority = new Authority();
    authority.setAuthority("ROLE_USER");
    authority.setUser(user);
    
    Set<Authority> authorities = new HashSet<>();
    authorities.add(authority);
    
    user.setAuthorities(authorities);
    user = userRepo.save(user);
    
    return user;
  }
}
