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

public class CreatedMealsFragment extends Fragment {

private RecyclerView createdMealList;
private CreatedMealsViewModel createdMealsViewModel;


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_created_meals, container, false);
    createdMealList = view.findViewById(R.id.created_meals_recycler_view);
    view.findViewById(R.id.add_meal).setOnClickListener((v) -> editMeal(0L));
    return view; //TODO add other onclick listeners for delete and schedule
  }

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
  }



  private void editMeal(Long id) {
    MealsToDetails action = CreatedMealsFragmentDirections.mealsToDetails();
    action.setMealId(id);
    Navigation.findNavController(getView()).navigate(action);
 }

  private void scheduleMeal(Long id) {
//    EditMeal action = CreatedMealsFragmentDirections.editMeal();
//    action.setMealId(id);
//    Navigation.findNavController(getView()).navigate(action);
  }


}