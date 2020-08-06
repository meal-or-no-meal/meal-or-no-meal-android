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

package edu.cnm.deepdive.mealornomeal.controller.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.mealornomeal.R;
import edu.cnm.deepdive.mealornomeal.model.Calendar;
import edu.cnm.deepdive.mealornomeal.model.Calendar.MealSlot;
import edu.cnm.deepdive.mealornomeal.model.Meal;
import edu.cnm.deepdive.mealornomeal.viewmodel.CalendarViewModel;
import edu.cnm.deepdive.mealornomeal.viewmodel.CreatedMealsViewModel;
import java.time.LocalDate;
import org.jetbrains.annotations.NotNull;


/**
 * The type Meal schedule fragment.
 */
public class MealScheduleFragment extends DialogFragment {

  private static final String MEAL_ID_KEY = "id";

  private long mealId;
  private View root;
  private AlertDialog dialog;
  private CalendarViewModel calendarViewModel;
  private DatePicker date;
  private Spinner mealSlot;


  /**
   * New instance meal schedule fragment.
   *
   * @param id the id
   * @return the meal schedule fragment
   */
  public static MealScheduleFragment newInstance(long id) {
    MealScheduleFragment fragment = new MealScheduleFragment();
    Bundle args = new Bundle();
    args.putLong(MEAL_ID_KEY, id);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Bundle args = getArguments();
    mealId = (args != null) ? args.getLong(MEAL_ID_KEY, 0) : 0;
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(
      @Nullable Bundle savedInstanceState) {
    super.onCreateDialog(savedInstanceState);
    root = LayoutInflater.from(getContext()).inflate(R.layout.fragment_meal_schedule, null, false);
    date = root.findViewById(R.id.date);
    mealSlot = root.findViewById(R.id.meal_slot);
    ArrayAdapter<MealSlot> adapter =
        new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, MealSlot.values());
    mealSlot.setAdapter(adapter);
    dialog = new AlertDialog.Builder(getContext())
    //    .setIcon()
        .setTitle("Schedule Meal")
        .setView(root)
        .setNegativeButton(android.R.string.cancel, null)
        .setPositiveButton(android.R.string.ok, (d, w) -> save())
        .create();
    return dialog;
  }

  private void save() {
    Calendar calendar = new Calendar();
    Meal meal = new Meal();
    meal.setId(mealId);
    calendar.setMeal(meal);
    calendar.setMealSlot((MealSlot) mealSlot.getSelectedItem());
    calendar.setDate(LocalDate.of(date.getYear(), date.getMonth()+1, date.getDayOfMonth()));
    calendarViewModel.save(calendar);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return root;
  }

  @Override
  public void onViewCreated(@NonNull @NotNull View view,
      @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    calendarViewModel = new ViewModelProvider(this).get(CalendarViewModel.class);
  }
}