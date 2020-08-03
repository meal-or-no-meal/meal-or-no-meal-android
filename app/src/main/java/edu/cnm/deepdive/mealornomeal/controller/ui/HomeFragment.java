//package edu.cnm.deepdive.mealornomeal.controller.ui;
//
//import android.os.Bundle;
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import androidx.navigation.NavController;
//import androidx.navigation.Navigation;
//import edu.cnm.deepdive.mealornomeal.R;
//
//// TODO Necessary to delete
//public class HomeFragment extends Fragment {
//
//  public HomeFragment() {
//
//  }
//
//
//  @Override
//  public View onCreateView(LayoutInflater inflater, ViewGroup container,
//      Bundle savedInstanceState) {
//    // Inflate the layout for this fragment
//    return inflater.inflate(R.layout.fragment_home, container, false);
//  }
//
//  @Override
//  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//    super.onViewCreated(view, savedInstanceState);
//    NavController navController = Navigation.findNavController(getView());
//    view.findViewById(R.id.calendar).setOnClickListener((v) ->
//        navController.navigate(R.id.navigation_calendar));
//    view.findViewById(R.id.ingredient).setOnClickListener((v) ->
//        navController.navigate(R.id.navigation_ingredients));
//  }
//}
