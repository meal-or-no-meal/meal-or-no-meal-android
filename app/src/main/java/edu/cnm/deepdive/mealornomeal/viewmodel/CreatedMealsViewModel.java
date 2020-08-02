package edu.cnm.deepdive.mealornomeal.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.mealornomeal.model.Meal;
import edu.cnm.deepdive.mealornomeal.service.GoogleSignInService;
import edu.cnm.deepdive.mealornomeal.service.MealRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CreatedMealsViewModel extends AndroidViewModel implements LifecycleObserver {

  private final MutableLiveData<Meal> meal;
  private final MutableLiveData<List<Meal>> meals;
  private final MutableLiveData<Throwable> throwable;
  private final MealRepository mealRepository;
  private final CompositeDisposable pending;
  private final Map<Long, Meal> mealMap;


  public CreatedMealsViewModel(@NonNull Application application) {
    super(application);
    meal = new MutableLiveData<Meal>();
    meals = new MutableLiveData<List<Meal>>();
    throwable = new MutableLiveData<>();
    mealRepository = MealRepository.getInstance();
    pending = new CompositeDisposable();
    mealMap = new HashMap<>();
    refreshMeals();
  }


  public LiveData<List<Meal>> getMeals() {
    return meals;
  }


  public void refreshMeals() {
    throwable.postValue(null);
    GoogleSignInService.getInstance().refresh()
        .addOnSuccessListener((account) -> {
          pending.add(
              mealRepository.getAllMeals(account.getIdToken())
                  .subscribe(
                      (meals) -> {
                        this.meals.postValue(meals);
                        for (Meal meal: meals) {
                          mealMap.put(meal.getId(), meal);
                        }
                      },
                      throwable::postValue
                  )
          );
        })
        .addOnFailureListener(throwable::postValue);
  }

  public void deleteMeal(Meal meal) {
    throwable.setValue(null);
    GoogleSignInService.getInstance().refresh()
        .addOnSuccessListener((account) -> {
          pending.add(
              mealRepository.delete(meal, account.getIdToken())
                  .subscribe(
                      () -> {
                        this.meal.postValue(null);
                        refreshMeals();
                      },
                      throwable::postValue
                  )
          );
        })
        .addOnFailureListener(throwable::postValue);
  }




}
