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

package edu.cnm.deepdive.mealornomeal.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.mealornomeal.R;
import edu.cnm.deepdive.mealornomeal.model.Meal;
import edu.cnm.deepdive.mealornomeal.view.CreatedMealsAdapter.Holder;
import java.util.List;

/**
 * The type Created meals adapter.
 */
public class CreatedMealsAdapter extends RecyclerView.Adapter<Holder> {

  private final String unnamedMeal;
  private final Context context;
  private final List<Meal> meals;
  private final OnEditListener onEditListener;
  private final OnDeleteListener onDeleteListener;
  private final OnScheduleListener onScheduleListener; //TODO implement scheduling functionality

  /**
   * Instantiates a new Created meals adapter.
   *
   * @param context            the context
   * @param meals              the meals
   * @param onEditListener     the on edit listener
   * @param onDeleteListener   the on delete listener
   * @param onScheduleListener the on schedule listener
   */
  public CreatedMealsAdapter(Context context,
      List<Meal> meals, OnEditListener onEditListener,
      OnDeleteListener onDeleteListener,
      OnScheduleListener onScheduleListener) {
    this.context = context;
    this.meals = meals;
    this.onEditListener = onEditListener;
    this.onDeleteListener = onDeleteListener;
    this.onScheduleListener = onScheduleListener; //TODO implement scheduling functionality
    unnamedMeal = "Mystery Meal";
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.created_meal_item, parent, false);
    return new Holder(view);
  }

  @Override
  public void onBindViewHolder(
      @NonNull Holder holder, int position) {
    holder.bind(position);
  }

  @Override
  public int getItemCount() {
    return meals.size();
  }

  /**
   * The type Holder.
   */
  class Holder extends RecyclerView.ViewHolder {

    private final View createdMealsView;
    private final TextView mealName;
    private final TextView prepTime;
    private final View edit;
    private final View delete;
    private final View scheduleMeal;


    /**
     * Instantiates a new Holder.
     *
     * @param createdMealsView the created meals view
     */
    public Holder(@NonNull View createdMealsView) {
      super(createdMealsView);
      this.createdMealsView = createdMealsView;
      mealName = createdMealsView.findViewById(R.id.edit_name);
      prepTime = createdMealsView.findViewById(R.id.prep_time);
      edit = createdMealsView.findViewById(R.id.edit);
      delete = createdMealsView.findViewById(R.id.delete);
      scheduleMeal = createdMealsView.findViewById(R.id.schedule_meal);
    }

    /**
     * binds elements together.
     * @param position
     */
    private void bind(int position) {
      Meal meal = meals.get(position);
      String name =
          (meal.getName() != null) ? meal.getName() : unnamedMeal;
      mealName.setText(meal.getName());
      prepTime.setText(meal.getPrepTime().toString());
      edit.setOnClickListener((v) -> onEditListener.onEdit(meal));
      delete.setOnClickListener((v) -> onDeleteListener.onDelete(meal));
      scheduleMeal.setOnClickListener((v) -> onScheduleListener.onSchedule(meal)); //TODO implement scheduling functionality
    }

  }

  /**
   * The interface On edit listener.
   */
  public interface OnEditListener {

    /**
     * On edit.
     *
     * @param meal the meal
     */
    void onEdit(Meal meal);
  }

  /**
   * The interface On delete listener.
   */
  public interface OnDeleteListener {

    /**
     * On delete.
     *
     * @param meal the meal
     */
    void onDelete(Meal meal);
  }

  /**
   * The interface On schedule listener.
   */
  public interface OnScheduleListener {

    /**
     * On schedule.
     *
     * @param meal the meal
     */
    void onSchedule(Meal meal);
  }

}
