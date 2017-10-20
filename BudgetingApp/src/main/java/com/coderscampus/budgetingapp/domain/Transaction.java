package com.coderscampus.budgetingapp.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction
{
  private Long id;
  private Date date;
  private BigDecimal total;
  private String type;
  private String note;
  
  public Long getId()
  {
    return id;
  }
  public void setId(Long id)
  {
    this.id = id;
  }
  public Date getDate()
  {
    return date;
  }
  public void setDate(Date date)
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
}
