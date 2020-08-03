package edu.cnm.deepdive.mealornomeal.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.mealornomeal.R;
import edu.cnm.deepdive.mealornomeal.model.Ingredient;
import edu.cnm.deepdive.mealornomeal.model.Meal;
import edu.cnm.deepdive.mealornomeal.view.IngredientListAdapter.Holder;
import java.util.List;

public class IngredientListAdapter extends RecyclerView.Adapter<Holder> {

  private final Context context;
  private final List<Ingredient> ingredients;
  private final OnIngredientClickListener listener;

  public IngredientListAdapter(Context context, List<Ingredient> ingredients, OnIngredientClickListener listener) {
    this.context = context;
    this.ingredients = ingredients;
    this.listener = listener;
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View root = LayoutInflater.from(context).inflate(R.layout.fragment_list, parent, false); //TODO Verify layout
    return new Holder(root);
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    holder.bind(position, ingredients.get(position));
  }

  @Override
  public int getItemCount() {
    return ingredients.size();
  }

  @FunctionalInterface
  public interface OnIngredientClickListener {

    void onIngredientClick(int position, Ingredient ingredient);

  }

  class Holder extends RecyclerView.ViewHolder {

    private final TextView ingredientText;
    private final TextView ingredientMeal;

    private Holder(View root) {
      super(root);
      ingredientText = root.findViewById(R.id.ingredient);
    }

    private void bind(int position, Ingredient ingredient) {
      ingredientText.setText(context.getString(R.string.ingredient_format, ingredient.getName()));
      Meal meal = ingredient.getName();
      String name = (meal != null) ? meal.getName() : null;
      String attribution = (name != null)
          ? context.getString(R.string.attribution_format, name)
          : context.getString(R.string.unattributed_meal);
      ingredientMeal.setText(attribution);
      itemView.setOnClickListener((v) -> listener.onIngredientClick(getAdapterPosition(), ingredient));
      itemView.setTag(ingredient);
    }

  }

}