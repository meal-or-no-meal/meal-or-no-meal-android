package edu.cnm.deepdive.mealornomeal.service;

import android.content.Context;
import edu.cnm.deepdive.mealornomeal.model.Ingredient;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IngredientRepository {

  private static final String AUTH_HEADER_FORMAT = "Bearer %s";
  private static final int NETWORK_POOL_SIZE = 4;

  private final Context context;
  private final BackEndService backEndService;
  private final ExecutorService networkPool;

  public IngredientRepository(Context context) {
    this.context = context;
    backEndService = BackEndService.getInstance();
    networkPool = Executors.newFixedThreadPool(NETWORK_POOL_SIZE);
  }

  public Single<List<Ingredient>> getAll(String idToken) {
    return backEndService.get(getHeader(idToken))
        .subscribeOn(Schedulers.from(networkPool));
  }


  public Single<Ingredient> save(Ingredient ingredient, String idToken) {
    return backEndService.post(getHeader(idToken), ingredient)
        .subscribeOn(Schedulers.from(networkPool));
  }

  public Completable delete(Ingredient ingredient, String idToken) {
    return backEndService.delete(getHeader(idToken), ingredient.getId())
        .subscribeOn(Schedulers.from(networkPool));
  }

  private String getHeader(String idToken) {
    return String.format(AUTH_HEADER_FORMAT, idToken);
  }

}