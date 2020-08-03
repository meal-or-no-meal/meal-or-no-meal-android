package edu.cnm.deepdive.mealornomeal.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.mealornomeal.model.Ingredient;
import edu.cnm.deepdive.mealornomeal.service.BackEndService;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class ListViewModel extends AndroidViewModel implements LifecycleObserver {

  private final MutableLiveData<List<Ingredient>> ingredients;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;
  private final BackEndService backEndService; //TODO Connect to ingredient repository?

  public ListViewModel(@NonNull Application application) {
    super(application);
    ingredients = new MutableLiveData<List<Ingredient>>();
    throwable = new MutableLiveData<Throwable>();
    backEndService = BackEndService.getInstance(); //Todo 'getInstance' or 'newInstance'
    pending = new CompositeDisposable();
 //   loadData();
  }

  public LiveData<List<Ingredient>> getIngredients() {
    return ingredients;
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  //TODO Adapt below code to MONM application
//  public void loadData() {
//    pending.add(
//      //  imgurService.getSearchResult(BuildConfig.CLIENT_ID,
//        //    "Cars")
//            .subscribeOn(Schedulers.io())
//            .map((result) -> {
//              List<Ingredient> ingredients = result.getData();
//              ingredients.removeIf((gallery) ->
//                  gallery.getImages() == null ||
//                      gallery.getImages().isEmpty());
//              return ingredients;
//            })
//            .subscribe(
//                value -> ListViewModel.this.ingredients.postValue(value),
//                throwable -> this.throwable.postValue(throwable.getCause())
//            )
//    );
//  }

  @Override
  protected void onCleared() {
    super.onCleared();
    pending.clear();
  }
}