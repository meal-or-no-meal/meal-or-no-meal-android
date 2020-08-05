package edu.cnm.deepdive.mealornomeal.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.mealornomeal.R;
import edu.cnm.deepdive.mealornomeal.model.Meal;
import java.util.List;

public class MealDetailAdapter extends RecyclerView.Adapter<MealDetailAdapter.Holder> {

  private final String unnamedMeal;
  private final Context context;
  private final List<Meal> meals;
  private final OnClickListener clickListener;

  public MealDetailAdapter(List<Meal> meals, Context context,
      OnClickListener clickListener) {
    super ();
    unnamedMeal = "Mystery Meal";
    this.context = context;
    this.clickListener = clickListener;
    this.meals = meals;
  }


  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.fragment_meal_detail, parent, false);
    return new Holder(view);
  }

  @Override
  public void onBindViewHolder(
      @NonNull MealDetailAdapter.Holder holder, int position) {
    holder.bind(position);
  }

  @Override
  public int getItemCount() {
    return meals.size();
  }


  public class Holder extends RecyclerView.ViewHolder {

    private final View mealDetailView;
    private final EditText prepTime;
    private final EditText mealName;
    private final EditText ingredients;
    private final EditText recipe;
    private final EditText equipment;

    public Holder(@NonNull View mealDetailView) {
      super(mealDetailView);
      this.mealDetailView = mealDetailView;
      prepTime = mealDetailView.findViewById(R.id.prep_time);
      mealName = mealDetailView.findViewById(R.id.edit_name);
      ingredients = mealDetailView.findViewById(R.id.ingredients);
      recipe = mealDetailView.findViewById(R.id.recipe);
      equipment = mealDetailView.findViewById(R.id.equipment_needed);
    }

    private void bind(int position) {
      Meal meal = MealDetailAdapter.this.meals.get(position);
      String name =
          (meal.getName() != null) ? meal.getName() : unnamedMeal;
      mealName.setText(meal.getName());
      prepTime.setText(Integer.parseInt(meal.getPrepTime().toString()));
//      ingredients.setText(meal.getIngredients()); //TODO connect this to ingredients
      recipe.setText(meal.getInstruction());
      equipment.setText(meal.getRequirements());
      mealDetailView.setOnClickListener((v) -> clickListener.OnClick(v, getAdapterPosition(), meal));
    }


  }

  public interface OnClickListener {
    void OnClick(View v, int position, Meal meal);
  }


}
