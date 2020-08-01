package edu.cnm.deepdive.mealornomeal.controller.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import edu.cnm.deepdive.mealornomeal.R;



public class CalendarFragment {

  private Button back;


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.calendar_fragment, container, false);
    back.setOnClickListener((v) -> editQuote(0));
    return root;


  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    View view = LayoutInflater.from()
        .inflate(R.layout.calendar_fragment, null, false);
    TextView breakfastSlot = view.findViewById(R.id.breakfast);
    TextView lunchSlot = view.findViewById(R.id.lunch);
    TextView dinnerSlot = view.findViewById(R.id.dinner);

  }


}


