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

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.mealornomeal.R;
import edu.cnm.deepdive.mealornomeal.controller.ui.CreatedMealsFragmentDirections.MealsToDetails;
import edu.cnm.deepdive.mealornomeal.view.CreatedMealsAdapter;
import edu.cnm.deepdive.mealornomeal.viewmodel.CreatedMealsViewModel;

/**
 * This is the meal fragment for the application. From here you view a list of all currently existing meals
 * which are displayed with a recyclerview. There is a floating action button which sends the User to
 * the MealDetails fragment where they can create a new meal.
 */
public class CreatedMealsFragment extends Fragment {

private RecyclerView createdMealList;
private CreatedMealsViewModel createdMealsViewModel;

  /**
   * Current setup code which shows a meal in its set layout enables an edit button on each meal
   */
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_created_meals, container, false);
    createdMealList = view.findViewById(R.id.created_meals_recycler_view);
    view.findViewById(R.id.add_meal).setOnClickListener((v) -> editMeal(null));
    return view; //TODO add other onclick listeners for delete and schedule
  }

  /**
   * Current setup code which shows a meal in its set layout enables an edit button and schedule
   * button on each meal
   */

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    createdMealsViewModel = new ViewModelProvider(getActivity())
        .get(CreatedMealsViewModel.class);
    createdMealsViewModel.getMeals().observe(getViewLifecycleOwner(), (meals) -> {
      if (meals != null) {
        createdMealList.setAdapter(new CreatedMealsAdapter(getContext(), meals,
            (meal) -> editMeal(meal.getId()),
            (meal) -> createdMealsViewModel.deleteMeal(meal),
            (meal) -> scheduleMeal(meal.getId())));
      }
    });
    createdMealsViewModel.refreshMeals();
  }



  private void editMeal(Long id) {
    MealsToDetails action = CreatedMealsFragmentDirections.mealsToDetails();
    action.setMealId((id != null) ? id.longValue() : 0);
    Navigation.findNavController(getView()).navigate(action);
 }

  private void scheduleMeal(Long id) {
    MealScheduleFragment fragment = MealScheduleFragment.newInstance(id);
    fragment.show(getChildFragmentManager(), fragment.getClass().getName());
  }


}