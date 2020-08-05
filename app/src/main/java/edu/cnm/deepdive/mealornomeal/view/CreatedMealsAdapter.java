package edu.cnm.deepdive.mealornomeal.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.mealornomeal.R;
import edu.cnm.deepdive.mealornomeal.model.Meal;
import edu.cnm.deepdive.mealornomeal.view.CreatedMealsAdapter.Holder;
import java.util.List;

public class CreatedMealsAdapter extends RecyclerView.Adapter<Holder> {

  private final String unnamedMeal;
  private final Context context;
  private final List<Meal> meals;
  private final OnEditListener onEditListener;
  private final OnDeleteListener onDeleteListener;
//  private final OnScheduleListener onScheduleListener; //TODO implement scheduling functionality

  public CreatedMealsAdapter(Context context,
      List<Meal> meals, OnEditListener onEditListener,
      OnDeleteListener onDeleteListener,
      OnScheduleListener onScheduleListener) {
    this.context = context;
    this.meals = meals;
    this.onEditListener = onEditListener;
    this.onDeleteListener = onDeleteListener;
//    this.onScheduleListener = onScheduleListener; //TODO implement scheduling functionality
    unnamedMeal = "Mystery Meal";
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.created_meal_item, parent, false);
    return new Holder(view);
  }

  @Override
  public void onBindViewHolder(
      @NonNull Holder holder, int position) {
    holder.bind(position);
  }

  @Override
  public int getItemCount() {
    return meals.size();
  }

  class Holder extends RecyclerView.ViewHolder {

    private final View createdMealsView;
    private final TextView mealName;
    private final TextView prepTime;
    private final View edit;
    private final View delete;
    private final View scheduleMeal;


    public Holder(@NonNull View createdMealsView) {
      super(createdMealsView);
      this.createdMealsView = createdMealsView;
      mealName = createdMealsView.findViewById(R.id.edit_name);
      prepTime = createdMealsView.findViewById(R.id.prep_time);
      edit = createdMealsView.findViewById(R.id.edit);
      delete = createdMealsView.findViewById(R.id.delete);
      scheduleMeal = createdMealsView.findViewById(R.id.schedule_meal);
    }

    private void bind(int position) {
      Meal meal = meals.get(position);
      String name =
          (meal.getName() != null) ? meal.getName() : unnamedMeal;
      mealName.setText(meal.getName());
      prepTime.setText(meal.getPrepTime().toString());
      edit.setOnClickListener((v) -> onEditListener.onEdit(meal));
      delete.setOnClickListener((v) -> onDeleteListener.onDelete(meal));
//      scheduleMeal.setOnClickListener((v) -> onScheduleListener.onSchedule(meal)); //TODO implement scheduling functionality
    }

  }

  public interface OnEditListener {

    void onEdit(Meal meal);
  }

  public interface OnDeleteListener {

    void onDelete(Meal meal);
  }

  public interface OnScheduleListener {

    void onSchedule(Meal meal);
  }

}
