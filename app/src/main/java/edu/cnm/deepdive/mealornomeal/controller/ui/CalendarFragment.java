package edu.cnm.deepdive.mealornomeal.controller.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.mealornomeal.R;
import edu.cnm.deepdive.mealornomeal.viewmodel.MealDetailViewModel;



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
        .setPositiveButton(android.R.string.ok, (dlg, which) -> save())
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

  private void save() {
//    .setText(quoteText.getText().toString().trim());
//    Source source = null;
//    String name = sourceName.getText().toString().trim();
//    if (!name.isEmpty()) {
//      for (Source s : sources) {
//        if (name.equalsIgnoreCase(s.getName())) {
//          source = s;
//          break;
//        }
//      }
//      if (source == null) {
//        source = new Source();
//        source.setName(name);
//      }
//    }
//    quote.setSource(source);
//    viewModel.save(quote);
//  }
  }

}
