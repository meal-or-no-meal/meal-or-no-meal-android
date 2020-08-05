package edu.cnm.deepdive.mealornomeal.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.mealornomeal.R;
import edu.cnm.deepdive.mealornomeal.model.Ingredient;
import edu.cnm.deepdive.mealornomeal.view.IngredientListAdapter.Holder;
import java.util.List;

public class IngredientListAdapter extends RecyclerView.Adapter<Holder> {

  private final Context context;
  private final List<Ingredient> ingredients;
  private final OnIngredientClickListener listener;
  private final String unnamedIngredient;

  public IngredientListAdapter(Context context, List<Ingredient> ingredients, OnIngredientClickListener listener) {
    this.context = context;
    this.ingredients = ingredients;
    this.listener = listener;
    unnamedIngredient = "Mystery Ingredient";
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View root = LayoutInflater.from(context).inflate(R.layout.fragment_list, parent, false); //TODO Verify layout
    return new Holder(root);
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    holder.bind(position);
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

    private final TextView ingredientName;

    private Holder(View root) {
      super(root);
      ingredientName = root.findViewById(R.id.ingredient_name);
    }

    private void bind(int position) {
      Ingredient ingredient = ingredients.get(position);
      String name =
          (ingredient.getName() != null) ? ingredient.getName() : unnamedIngredient;
      ingredientName.setText(ingredient.getName());
      itemView.setOnClickListener((v) -> listener.onIngredientClick(getAdapterPosition(), ingredient));
      itemView.setTag(ingredient);
    }

  }

}