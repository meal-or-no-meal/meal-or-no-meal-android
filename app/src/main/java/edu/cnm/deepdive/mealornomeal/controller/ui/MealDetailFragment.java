package edu.cnm.deepdive.mealornomeal.controller.ui;

import android.widget.EditText;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import edu.cnm.deepdive.mealornomeal.R;
import edu.cnm.deepdive.mealornomeal.model.Meal;
import edu.cnm.deepdive.mealornomeal.viewmodel.MealDetailViewModel;

public class MealDetailFragment extends Fragment {

private static final String ID_KEY = "meal_id";

  private EditText prepTime;
  private EditText mealName;
  private EditText ingredients;
  private EditText recipe;
  private EditText requirements;
  private long mealId;
  private Meal meal;
  private MealDetailViewModel mealDetailViewModel;


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  // mealId = MealDetailFragmentArgs.fromBundle(getArguments()).getMealId();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_meal_detail, container, false);
    prepTime = view.findViewById(R.id.prep_time);
    mealName = view.findViewById(R.id.meal_name);
    ingredients = view.findViewById(R.id.ingredients);
    recipe = view.findViewById(R.id.recipe);
    requirements = view.findViewById(R.id.equipment_needed);

    view.findViewById(R.id.save_details).setOnClickListener((v) -> {
      save();
      //TODO add navigation back to meal list
    });
    return view;
  }

  private void save() {
    meal.setName(mealName.getText().toString().trim());
    String prepTimeText = prepTime.getText().toString().trim();
    meal.setPrepTime(prepTimeText.isEmpty() ? 0 : Integer.parseInt(prepTimeText));
//    meal.setIngredients(ingredients.getText().toString().trim()); //TODO connect this to ingredients
    meal.setRequirements(requirements.getText().toString().trim());
    meal.setInstruction(recipe.getText().toString().trim());
    mealDetailViewModel.saveMeal(meal);
  }


  @Override
  public void onViewCreated(@NonNull View view,
      @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mealDetailViewModel = new ViewModelProvider(getActivity()).get(MealDetailViewModel.class);
    if (mealId != 0) {
      mealDetailViewModel.getMeal().observe(getViewLifecycleOwner(), (meal) -> {
        this.meal = meal;
        mealName.setText(meal.getName());
        prepTime.setText(String.valueOf(meal.getPrepTime()));
//        ingredients.setText(meal.getIngredients()); //TODO connect this to ingredients
        recipe.setText(meal.getInstruction());
        requirements.setText(meal.getRequirements());
      });
      mealDetailViewModel.setMealId(mealId);
    } else {
      meal = new Meal();
    }
  }


}