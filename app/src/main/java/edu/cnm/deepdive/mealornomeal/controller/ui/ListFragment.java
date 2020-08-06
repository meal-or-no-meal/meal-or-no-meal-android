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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.mealornomeal.R;
import edu.cnm.deepdive.mealornomeal.model.Ingredient;
import edu.cnm.deepdive.mealornomeal.view.IngredientListAdapter;
import edu.cnm.deepdive.mealornomeal.view.IngredientListAdapter.OnIngredientClickListener;
import edu.cnm.deepdive.mealornomeal.viewmodel.ListViewModel;

/**
 * * ListFragment is a detailed view at the elements of ingredients from meals.
 *  * Here the User can generate the list of all meals for a set time period.
 */
public class ListFragment extends Fragment implements OnIngredientClickListener {

    private RecyclerView ingredientArray;
    private ListViewModel viewModel;


  /**
   * General setup for the connecting the MealDetail layout with the controller and viewmodel.
   * Inflates the fragment and sets up the ingredientArray for selecting
   * an ingredient. It also enables the Save button functionality saving any edited fields
   * to the database.
   */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_list, container, false);
      ingredientArray = view.findViewById(R.id.ingredient_list);
      return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      viewModel = new ViewModelProvider(getActivity())
          .get(ListViewModel.class);
      viewModel.getIngredients().observe(getViewLifecycleOwner(), (ingredients) -> {
        if (ingredients != null) {
          ingredientArray.setAdapter(new IngredientListAdapter(getContext(),
              ingredients,
              this));
        }
      });
    }

  @Override
  public void onIngredientClick(int position, Ingredient ingredient) {
// TODO - MOVE TO RECYCLERVIEW change box to checkbox when ingredient it clicked in list
  }

    }
