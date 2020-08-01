package edu.cnm.deepdive.mealornomeal.viewmodel;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.CompositeDisposable;

//public class MealDetailViewModel extends AndroidViewModel {

//  private final MealRepository mealRepository;
//  private final MutableLiveData<Meal> meal;
//  private final MutableLiveData<Throwable> throwable;
//  private final CompositeDisposable pending;
//
//
//  public MealDetailViewModel(@NonNull Application application) {
//    super(application);
//    mealRepository = new MealRepository(application);
//    throwable = new MutableLiveData<>();
//    pending = new CompositeDisposable();
//    meal = new MutableLiveData<>();
//  }
//
//  public LiveData<Meal> getMeal() {return meal;}
//
//  public LiveData<Throwable> getThrowavle() {
//    return throwable;
//  }
//
//  public void setMealId(long id) {
//    throwable.setValue(null);
//    pending.add(
//        mealRepository.get(id)
//            .subscribe(
//                (alarm) -> this.meal.postValue(meal),
//                (throwable) -> this.throwable.postValue(throwable)
//            )
//    );
//  }
//
//
//  public void saveMeal(Meal meal) {
//    throwable.setValue(null);
//    pending.add(
//        mealRepository.save(meal)
//            .subscribe(
//                () -> {
//                  Log.d(getClass().getName(), "Save Completed");
//                },
//                (throwable) -> this.throwable.postValue(throwable.getCause())
//            ));
//  }



//}