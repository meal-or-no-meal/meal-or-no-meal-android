package edu.cnm.deepdive.mealornomeal.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;

//public class CreatedMealsViewModel extends AndroidViewModel implements LifecycleObserver {
//
//  private MutableLiveData<Meal> meal_item;
//  private MutableLiveData<List<Meal>> meals;
//  MutableLiveData<Throwable> throwable;
//
//
//  public CreatedMealsViewModel(@NonNull Application application) {
//    super(application);
//    meal_item = new MutableLiveData<Meal>();
//    meals = new MutableLiveData<List<Meal>>();
//    throwable = new MutableLiveData<>();
//  }
//
//
//  public LiveData<List<Meal>> getMeals() {
//    return mealRepository.getMeals();
//  }
//
//  public void deleteMeal(Meal meal) {
//    throwable.setValue(null);
//    pending.add(
//        mealRepository.delete(meal)
//            .subscribe(
//                () -> {},
//                this.throwable::postValue
//            )
//    );
//  }
//
//}
