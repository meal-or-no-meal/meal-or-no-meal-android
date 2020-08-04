package edu.cnm.deepdive.mealornomeal.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.mealornomeal.model.Meal;
import edu.cnm.deepdive.mealornomeal.service.GoogleSignInService;
import edu.cnm.deepdive.mealornomeal.service.MealRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.util.HashMap;
import java.util.Map;

public class MealDetailViewModel extends AndroidViewModel {

  private final MealRepository mealRepository;
  private final MutableLiveData<Meal> meal;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;
  private final Map<Long, Meal> mealMap;


  public MealDetailViewModel(@NonNull Application application) {
    super(application);
    mealRepository = new MealRepository();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
    meal = new MutableLiveData<>();
    mealMap = new HashMap<>();
  }

  public LiveData<Meal> getMeal() {return meal;}

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }


  public void saveMeal(Meal meal) {
    throwable.setValue(null);
    GoogleSignInService.getInstance().refresh()
        .addOnSuccessListener((account) -> {
          pending.add(
              mealRepository.save(account.getIdToken(), meal)
                  .subscribe(
                      () -> {
                        this.meal.postValue(null);
                      },
                      throwable::postValue
                  )
          );
        })
        .addOnFailureListener(throwable::postValue);
  }

  public void setMealId(Long id) {
    meal.setValue(mealMap.get(id));
  }

}