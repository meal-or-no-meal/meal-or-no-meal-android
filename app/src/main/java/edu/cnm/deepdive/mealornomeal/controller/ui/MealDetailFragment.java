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

import android.widget.EditText;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import edu.cnm.deepdive.mealornomeal.R;
import edu.cnm.deepdive.mealornomeal.model.Meal;
import edu.cnm.deepdive.mealornomeal.viewmodel.CreatedMealsViewModel;

/**
 * The MealDetailFragment is a detailed view at the elements of a single Meal.
 * Here the User can edit the Meal Name, Ingredients, Prep Time, Recipe, and Requirements of a meal.
 * This is also where the User is taken to create a new meal.
 */
public class MealDetailFragment extends Fragment {

private static final String ID_KEY = "meal_id";

  private EditText prepTime;
  private EditText mealName;
  private EditText ingredients;
  private EditText recipe;
  private EditText requirements;
  private long mealId;
  private Meal meal;
  private CreatedMealsViewModel mealsViewModel;


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  mealId = MealDetailFragmentArgs.fromBundle(getArguments()).getMealId();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_meal_detail, container, false);
    prepTime = view.findViewById(R.id.prep_time);
    mealName = view.findViewById(R.id.edit_name);
    ingredients = view.findViewById(R.id.ingredients);
    recipe = view.findViewById(R.id.recipe);
    requirements = view.findViewById(R.id.equipment_needed);

    view.findViewById(R.id.save_details).setOnClickListener((v) -> {
      save();
      NavDirections action = MealDetailFragmentDirections.detailsToMeals();
      Navigation.findNavController(getView()).navigate(action);
    });
    return view;
  }

  private void save() {
    meal.setName(mealName.getText().toString().trim());
    String prepTimeText = prepTime.getText().toString().trim();
    meal.setPrepTime(prepTimeText.isEmpty() ? 0 : Integer.parseInt(prepTimeText));
//    meal.setIngredients(ingredients.getText().toString().trim());
    meal.setRequirements(requirements.getText().toString().trim());
    meal.setInstruction(recipe.getText().toString().trim());
    mealsViewModel.saveMeal(meal);
  }


  @Override
  public void onViewCreated(@NonNull View view,
      @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mealsViewModel = new ViewModelProvider(getActivity()).get(CreatedMealsViewModel.class);
    if (mealId != 0) {

      mealsViewModel.getMeal().observe(getViewLifecycleOwner(), (meal) -> {
        this.meal = meal;
        mealName.setText(meal.getName());
        prepTime.setText(String.valueOf(meal.getPrepTime()));
//        ingredients.setText(meal.getIngredients()); //TODO connect this to ingredients
        recipe.setText(meal.getInstruction());
        requirements.setText(meal.getRequirements());
      });
      mealsViewModel.setMealId(mealId);
    } else {
      meal = new Meal();
    }
  }

}