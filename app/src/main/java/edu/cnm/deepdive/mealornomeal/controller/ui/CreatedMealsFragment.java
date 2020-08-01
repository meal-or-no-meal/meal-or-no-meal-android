package edu.cnm.deepdive.mealornomeal.controller.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.mealornomeal.R;
import edu.cnm.deepdive.mealornomeal.view.CreatedMealsAdapter;
//import edu.cnm.deepdive.mealornomeal.viewmodel.CreatedMealsViewModel;

public class CreatedMealsFragment extends Fragment {

private RecyclerView createdMealList;
//private CreatedMealsViewModel createdMealsViewModel;

//
//  @Override
//  public void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    if (getArguments() != null) {
//      mParam1 = getArguments().getString(ARG_PARAM1);
//      mParam2 = getArguments().getString(ARG_PARAM2);
//    }
//  }

//  @Override
//  public View onCreateView(LayoutInflater inflater, ViewGroup container,
//      Bundle savedInstanceState) {
//    View view = inflater.inflate(R.layout.fragment_created_meals, container, false);
//    createdMealList = view.findViewById(R.id.created_meals_recycler_view);
//    view.findViewById(R.id.back_button).setOnClickListener((v) -> navMyMeals());
//    view.findViewById(R.id.home_button).setOnClickListener((v) -> navHome());
//    return view;
//  }

//
//  @Override
//  public void onViewCreated(@NonNull View view,
//      @Nullable Bundle savedInstanceState) {
//    super.onViewCreated(view, savedInstanceState);
//    createdMealsViewModel = new ViewModelProvider(getActivity())
//        .get(CreatedMealsViewModel.class);
//    createdMealsViewModel.getMeals().observe(getViewLifecycleOwner(), (meals) -> {
//      if (meals != null) {
//        createdMealList.setAdapter(new CreatedMealsAdapter(getContext(), meals,
//            (meal) -> scheduleMeal(meal.getId()),
//            (meal) -> createdMealsViewModel.deleteMeal(meal)));
//      }
//    });
//  }

//  private void scheduleMeal(long id) {
//    EditMeal action = CreatedMealsFragmentDirections.editMeal();
//    action.setMealId(id);
//    Navigation.findNavController(getView()).navigate(action);
//  }
//
//  private void navHome() {
//    NavDirections action = CreatedMealsFragmentDirections.returnHome();
//    Navigation.findNavController(getView()).navigate(action);
//  }
//
//  private void navMyMeals() {
//    NavDirections action = CreatedMealsFragmentDirections.toMyMeals();
//    Navigation.findNavController(getView()).navigate(action);
//  }

}