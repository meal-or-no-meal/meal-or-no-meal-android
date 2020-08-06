/*
 * <!--
 *   Copyright 2020 Meal or no Meal
 *  Paul Cutter, Mickie Morlang, Ambar Rodriguez, Levi Sanchez
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0>
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * -->
 */

package edu.cnm.deepdive.mealornomeal.model;

import androidx.annotation.NonNull;
import com.google.gson.annotations.Expose;
import java.time.LocalDate;

/**
 * This Calendar class provides the elements to be able to retrieve and send data.
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
    BREAKFAST("Breakfast"),
    /**
     * Lunch meal slot.
     */
    LUNCH("Lunch"),
    /**
     * Dinner meal slot.
     */
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
