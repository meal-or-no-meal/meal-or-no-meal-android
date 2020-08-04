package edu.cnm.deepdive.mealornomeal.service;

import edu.cnm.deepdive.mealornomeal.model.Meal;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MealRepository {

  private static final String AUTH_HEADER_FORMAT = "Bearer %s";
  private static final int NETWORK_POOL_SIZE = 4;

  private final BackEndService backEndService;
  private final ExecutorService networkPool;

  public MealRepository() {
    backEndService = BackEndService.getInstance();
    networkPool = Executors.newFixedThreadPool(NETWORK_POOL_SIZE);
  }

  public static MealRepository getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public Single<List<Meal>> getAllMeals(String idToken) {
    return backEndService.getAllMeals(getHeader(idToken))
        .subscribeOn(Schedulers.from(networkPool));
  }

  public Single<Meal> getMeal(String idToken, Long id) {
    return backEndService.get(getHeader(idToken))
        .subscribeOn(Schedulers.from(networkPool));
  }

  public Completable save(String idToken, Meal meal) {
    if (meal.getId() == 0L) {
      return Completable.fromSingle(
          backEndService.postMeal(getHeader(idToken), meal)
              .subscribeOn(Schedulers.from(networkPool))
      );
    } else {
      return Completable.fromSingle(
          backEndService.putMeal(getHeader(idToken), meal, meal.getId())
              .subscribeOn(Schedulers.from(networkPool))
      );
    }
  }

  public Completable delete (Meal meal, String idToken) {
    if (meal.getId() == 0) {
      return Completable.fromAction(() -> {})
          .subscribeOn(Schedulers.io());
    } else {
      return backEndService.delete(getHeader(idToken), meal.getId())
              .subscribeOn(Schedulers.from(networkPool));
    }
  }

  private String getHeader(String idToken) {
    return String.format(AUTH_HEADER_FORMAT, idToken);
  }

  private static class InstanceHolder {

    private static final MealRepository INSTANCE = new MealRepository();

  }

}
