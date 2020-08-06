package edu.cnm.deepdive.mealornomeal.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import edu.cnm.deepdive.mealornomeal.model.Meal;
import edu.cnm.deepdive.mealornomeal.service.GoogleSignInService;
import edu.cnm.deepdive.mealornomeal.service.MealRepository;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * The type Created meals view model.
 */
public class CreatedMealsViewModel extends AndroidViewModel implements LifecycleObserver {

  private final MutableLiveData<Meal> meal;
  private final MutableLiveData<List<Meal>> meals;
  private final MutableLiveData<Throwable> throwable;
  private final MealRepository mealRepository;
  private final CompositeDisposable pending;
  private final Map<Long, Meal> mealMap;
  private final GoogleSignInService signInService;


  /**
   * Instantiates a new Created meals view model.
   *
   * @param application the application
   */
  public CreatedMealsViewModel(@NonNull Application application) {
    super(application);
    meal = new MutableLiveData<Meal>();
    meals = new MutableLiveData<List<Meal>>();
    throwable = new MutableLiveData<>();
    mealRepository = MealRepository.getInstance();
    pending = new CompositeDisposable();
    mealMap = new HashMap<>();
    signInService = GoogleSignInService.getInstance();
    refreshMeals();
  }


  /**
   * Gets meals.
   *
   * @return the meals
   */
  public LiveData<List<Meal>> getMeals() {
    return meals;
  }

  /**
   * Gets meal.
   *
   * @return the meal
   */
  public LiveData<Meal> getMeal() {
    return meal;
  }

  /**
   * Refresh meals.
   */
  public void refreshMeals() {
    refreshAndExecute((account) ->  mealRepository.getAllMeals(account.getIdToken())
        .subscribe(
            (meals) -> {
              this.meals.postValue(meals);
              for (Meal meal : meals) {
                mealMap.put(meal.getId(), meal);
              }
            },
            throwable::postValue
        )
    );
  }

  /**
   * Save meal.
   *
   * @param meal the meal
   */
  public void saveMeal(Meal meal) {
    refreshAndExecute((account) -> mealRepository.save(account.getIdToken(), meal)
        .subscribe(
            (m) -> {
              List<Meal> meals = this.meals.getValue();
              meals.add(m);
              mealMap.put(m.getId(), m);
              this.meals.postValue(meals);
            },
            throwable::postValue
        )
    );
  }

  /**
   * Delete meal.
   *
   * @param meal the meal
   */
  public void deleteMeal(Meal meal) {
    refreshAndExecute((account) -> mealRepository.delete(meal, account.getIdToken())
        .subscribe(
            () -> {
              this.meal.postValue(null);
              refreshMeals();
            },
            throwable::postValue
        )
    );
  }

  /**
   * Sets meal id.
   *
   * @param id the id
   */
  public void setMealId(long id) {
    meal.setValue(mealMap.get(id));
  }

  private void refreshAndExecute(AuthenticatedTask task) {
    throwable.setValue(null);
    signInService.refresh()
        .addOnSuccessListener((account) -> pending.add(task.execute(account)))
        .addOnFailureListener(throwable::postValue);
  }

  /**
   * The interface Authenticated task.
   */
  @FunctionalInterface
  public interface AuthenticatedTask {

    /**
     * Execute disposable.
     *
     * @param account the account
     * @return the disposable
     */
    Disposable execute(GoogleSignInAccount account);
  }

}
