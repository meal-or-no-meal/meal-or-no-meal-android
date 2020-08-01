package edu.cnm.deepdive.mealornomeal.model;

import com.google.gson.annotations.Expose;

public class Ingredient {

  @Expose
  private Long id;
  @Expose
  private String name;
  @Expose
  private String quantity;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getQuantity() { return quantity; }

  public void setQuantity(String quantity) { this.quantity = quantity; }
}
