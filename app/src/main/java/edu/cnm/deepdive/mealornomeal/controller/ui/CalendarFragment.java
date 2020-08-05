package edu.cnm.deepdive.mealornomeal.controller.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.mealornomeal.R;
import edu.cnm.deepdive.mealornomeal.viewmodel.MealDetailViewModel;


/**
 * The type Calendar fragment.
 */
public class CalendarFragment extends DialogFragment {

  private MealDetailViewModel viewModel;
  private AlertDialog alertDialog;


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_calendar, container, false);
    return root;


  }

  @NonNull
  @Override
  public Dialog onCreateDialog(
      @Nullable Bundle savedInstanceState) {
    View view = LayoutInflater.from(getContext())
        .inflate(R.layout.fragment_calendar, null, false);
    TextView breakfastSlot = view.findViewById(R.id.breakfast);
    TextView lunchSlot = view.findViewById(R.id.lunch);
    TextView dinnerSlot = view.findViewById(R.id.dinner);
    CalendarView calendar = view.findViewById(R.id.calendar_view);
    //TODO
    breakfastSlot.setText("");
    lunchSlot.setText("");
    dinnerSlot.setText("");
    alertDialog = new AlertDialog.Builder(getContext())
        .setTitle("Meals")
        .setView(view)
        .setNegativeButton(android.R.string.cancel, (dlg, which) -> {
        })
        .create();

    return alertDialog;
  }

  @Override
  public void onViewCreated(@NonNull @org.jetbrains.annotations.NotNull View view,
      @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(getActivity())
        .get(MealDetailViewModel.class);


  }


}
