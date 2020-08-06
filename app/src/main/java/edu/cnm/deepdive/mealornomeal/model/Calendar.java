package edu.cnm.deepdive.mealornomeal.model;

import androidx.annotation.NonNull;
import com.google.gson.annotations.Expose;
import java.time.LocalDate;

public class Calendar {

  @Expose
  private Long id;
  @Expose
  private Meal meal;
  @Expose
  private LocalDate date;
  @Expose
  private MealSlot mealSlot;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Meal getMeal() {
    return meal;
  }

  public void setMeal(Meal meal) {
    this.meal = meal;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public MealSlot getMealSlot() {
    return mealSlot;
  }

  public void setMealSlot(MealSlot mealSlot) {
    this.mealSlot = mealSlot;
  }

  public enum MealSlot {
    BREAKFAST("Breakfast"),
    LUNCH("Lunch"),
    DINNER("Dinner");

    private final String display;

    MealSlot(String display) {
      this.display = display;
    }

    @NonNull
    @Override
    public String toString() {
      return display;
    }
  }

}
