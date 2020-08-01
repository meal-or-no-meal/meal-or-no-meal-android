package edu.cnm.deepdive.mealornomeal.view;

import android.media.Image;
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
import edu.cnm.deepdive.mealornomeal.viewmodel.ListViewModel;

public class IngredientListAdapter extends RecyclerView.Adapter<IngredientListAdapter.IngredientViewHolder> {

  private final Context context;
  private final List<Ingredient> ingredients;
  private final OnItemSelectedHelper onItemSelectedHelper;

  public IngredientListAdapter(Context context, List<Ingredient> ingredients,
      OnItemSelectedHelper onItemSelectedHelper) {
    super();
    this.context = context;
    this.ingredients = ingredients;
    this.onItemSelectedHelper = onItemSelectedHelper;
  }

  @NonNull
  @Override
  public IngredientListAdapter.IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType) {
    View view = LayoutInflater.from(context).
        inflate(R.layout.item_ingredient_search, parent, false);
    return new IngredientViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull IngredientListAdapter.IngredientViewHolder holder, int position) {
    holder.bind(position);
  }

  @Override
  public int getItemCount() {

    return ingredients.size();
  }

  class IngredientViewHolder extends RecyclerView.ViewHolder implements OnItemSelectedListener{

    private final TextView title;
    private final TextView description;
    private final Spinner imageSpinner;
    private Ingredient ingredient;

    private final List<Image> withIconList = new ArrayList<>();
    private final String imageUrl = "" + R.drawable.ingredient;
    private final Image ingredientIcon = new Image(imageUrl);

    public IngredientViewHolder(@NonNull View itemView) {
      super(itemView);
      title = itemView.findViewById(R.id.ingredient_title);
      description = itemView.findViewById(R.id.ingredient_description);
      imageSpinner = itemView.findViewById(R.id.ingredient_search_spinner);
    }


    private void bind(int position) {
      ingredient = ingredients.get(position);
      withIconList.clear();
      withIconList.add(ingredientIcon);
      withIconList.addAll(ingredient.getImages());
      title.setText(ingredients.get(position).getTitle());
      description.setText(ingredients.get(position).getDescription());
      IngredientImageAdapter ingredientImageAdapter = new IngredientImageAdapter(context,
          withIconList);
      imageSpinner.setAdapter(ingredientImageAdapter);
      imageSpinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
      if(position > 0) {
        onItemSelectedHelper.onSelected(ingredient, ingredient.getImages().get(position - 1));
        adapterView.setSelection(0);
      }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
  }

  public interface OnItemSelectedHelper {
    void onSelected(Ingredient ingredient, Image image);
  }

}