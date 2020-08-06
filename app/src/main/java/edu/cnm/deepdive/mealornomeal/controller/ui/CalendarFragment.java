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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import edu.cnm.deepdive.mealornomeal.R;
import edu.cnm.deepdive.mealornomeal.controller.ui.CreatedMealsFragmentDirections.MealsToDetails;
import edu.cnm.deepdive.mealornomeal.model.Calendar;
import edu.cnm.deepdive.mealornomeal.model.Meal;
import edu.cnm.deepdive.mealornomeal.viewmodel.CalendarViewModel;
import edu.cnm.deepdive.mealornomeal.viewmodel.CreatedMealsViewModel;
import java.time.LocalDate;

/**
 * This Calendar fragment is responsible for the displaying of
 * data which it receives from the meals repository along with the data from the calendar layout.
 */

public class CalendarFragment extends Fragment implements OnDateChangeListener {

  private AlertDialog alertDialog;
  private TextView breakfastSlot;
  private TextView lunchSlot;
  private TextView dinnerSlot;
  private ImageView breakfastInfo;
  private ImageView lunchInfo;
  private ImageView dinnerInfo;
  private ImageView clearBreakfast;
  private ImageView clearLunch;
  private ImageView clearDinner;
  private CalendarView calendarView;
  private long calendarId;
  private CalendarViewModel calendarViewModel;
  private Calendar calendar;


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_calendar, container, false);



    breakfastSlot = view.findViewById(R.id.breakfast);
    breakfastInfo = view.findViewById(R.id.breakfast_info);
    clearBreakfast = view.findViewById(R.id.clear_breakfast);
    breakfastInfo.setOnClickListener((v) -> showInfo((Calendar) breakfastSlot.getTag()));
    clearBreakfast.setOnClickListener((v) -> calendarViewModel.delete((Calendar) breakfastSlot.getTag()));


    lunchSlot = view.findViewById(R.id.lunch);
    lunchInfo = view.findViewById(R.id.lunch_info);
    clearLunch = view.findViewById(R.id.clear_lunch);
    lunchInfo.setOnClickListener((v) -> showInfo((Calendar) lunchSlot.getTag()));
    clearLunch.setOnClickListener((v) -> calendarViewModel.delete((Calendar) lunchSlot.getTag()));

    dinnerSlot = view.findViewById(R.id.dinner);
    dinnerInfo = view.findViewById(R.id.dinner_info);
    clearDinner = view.findViewById(R.id.clear_dinner);
    dinnerInfo.setOnClickListener((v) -> showInfo((Calendar) dinnerSlot.getTag()));
    clearDinner.setOnClickListener((v) -> calendarViewModel.delete((Calendar) dinnerSlot.getTag()));


    CalendarView calendar = view.findViewById(R.id.calendar_view);
    calendar.setOnDateChangeListener(this);


    breakfastSlot.setText("");
    lunchSlot.setText("");
    dinnerSlot.setText("");
    return view;
  }

  @Override
  public void onViewCreated(@NonNull @org.jetbrains.annotations.NotNull View view,
      @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    calendarViewModel = new ViewModelProvider(this).get(CalendarViewModel.class);
    calendarViewModel.getCalendars().observe(getViewLifecycleOwner(), (calendars) -> {
      for (Calendar calendar : calendars) {
        switch (calendar.getMealSlot()) {
          case BREAKFAST:
            breakfastSlot.setText(calendar.getMeal().getName());
            breakfastSlot.setTag(calendar);
            break;
          case LUNCH:
            lunchSlot.setText(calendar.getMeal().getName());
            lunchSlot.setTag(calendar);
            break;
          case DINNER:
            dinnerSlot.setText(calendar.getMeal().getName());
            dinnerSlot.setTag(calendar);
            break;
        }
      }
    });
  }

  @Override
  public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
    LocalDate date = LocalDate.of(year, month + 1, dayOfMonth);
    calendarViewModel.setCalendarDate(date);
  }

  private void showInfo(Calendar calendar){
    Long id = calendar.getMeal().getId();
    MealsToDetails action = CreatedMealsFragmentDirections.mealsToDetails();
    action.setMealId((id != null) ? id.longValue() : 0);
    Navigation.findNavController(getView()).navigate(action);
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
