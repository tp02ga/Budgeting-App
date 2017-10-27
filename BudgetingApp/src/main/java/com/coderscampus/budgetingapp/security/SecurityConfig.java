package com.coderscampus.budgetingapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
  @Autowired
  private UserDetailsServiceImpl userDetailsService;
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception
  {
    auth.userDetailsService(userDetailsService);
  }
  
  @Override
  protected void configure(HttpSecurity http) throws Exception
  {
    http.csrf().disable()
        .authorizeRequests()
          .antMatchers("/js/*","/css/*").permitAll()
          .antMatchers("/register").permitAll()
//          .antMatchers("/admin/**").hasRole("ADMIN") // commented out on purpose, just for learning purposes
          .antMatchers("/**").hasRole("USER").and()
        .formLogin()
          .loginPage("/login")
          .defaultSuccessUrl("/budgets")
          .permitAll().and()
        .logout()
          .logoutSuccessUrl("/")
          .permitAll();
        
  }
}
