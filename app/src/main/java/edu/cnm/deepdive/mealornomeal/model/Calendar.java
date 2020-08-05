package edu.cnm.deepdive.mealornomeal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.time.LocalDate;

/**
 * The type Calendar.
 */
public class Calendar {

  @Expose
  private Long id;
  @Expose
  private Meal meal;
  @Expose
  private LocalDate date;
  @Expose
  private MealSlot mealSlot;

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
   * Gets meal.
   *
   * @return the meal
   */
  public Meal getMeal() {
    return meal;
  }

  /**
   * Sets meal.
   *
   * @param meal the meal
   */
  public void setMeal(Meal meal) {
    this.meal = meal;
  }

  /**
   * Gets date.
   *
   * @return the date
   */
  public LocalDate getDate() {
    return date;
  }

  /**
   * Sets date.
   *
   * @param date the date
   */
  public void setDate(LocalDate date) {
    this.date = date;
  }

  /**
   * Gets meal slot.
   *
   * @return the meal slot
   */
  public MealSlot getMealSlot() {
    return mealSlot;
  }

  /**
   * Sets meal slot.
   *
   * @param mealSlot the meal slot
   */
  public void setMealSlot(MealSlot mealSlot) {
    this.mealSlot = mealSlot;
  }

  /**
   * The enum Meal slot.
   */
  public enum MealSlot {
    /**
     * Breakfast meal slot.
     */
    BREAKFAST,
    /**
     * Lunch meal slot.
     */
    LUNCH,
    /**
     * Dinner meal slot.
     */
    DINNER
  }

}
