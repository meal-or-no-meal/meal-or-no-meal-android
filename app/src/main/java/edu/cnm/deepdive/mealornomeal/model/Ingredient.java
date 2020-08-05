package edu.cnm.deepdive.mealornomeal.model;

import com.google.gson.annotations.Expose;

/**
 * The type Ingredient.
 */
public class Ingredient {

  @Expose
  private Long id;
  @Expose
  private String name;
  @Expose
  private String quantity;


  /**
   * Gets id.
   *
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name.
   *
   * @param name the name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets quantity.
   *
   * @return the quantity
   */
  public String getQuantity() { return quantity; }

  /**
   * Sets quantity.
   *
   * @param quantity the quantity
   */
  public void setQuantity(String quantity) { this.quantity = quantity; }
}
