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
import edu.cnm.deepdive.mealornomeal.R;

/**
 * This Calendar fragment is responsible for the displaying of
 * data which it receives from the meals repository along with the data from the calendar layout.
 */

public class CalendarFragment extends DialogFragment {

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
