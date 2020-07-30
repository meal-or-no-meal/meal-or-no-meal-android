package edu.cnm.deepdive.mealornomeal.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import java.util.List;

public class CreatedMealsViewModel extends AndroidViewModel implements LifecycleObserver {

//  private MutableLiveData<Meal> meal_item;
//  private MutableLiveData<List<Meal>> meals;


  public CreatedMealsViewModel(@NonNull Application application) {
    super(application);
  }


}
