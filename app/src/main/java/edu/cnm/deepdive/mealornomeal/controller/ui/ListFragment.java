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

public class ListFragment extends Fragment implements OnIngredientClickListener {

    private RecyclerView ingredientArray;
    private ListViewModel viewModel;

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
