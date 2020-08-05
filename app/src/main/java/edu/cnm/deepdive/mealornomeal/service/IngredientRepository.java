package edu.cnm.deepdive.mealornomeal.service;

import android.content.Context;
import edu.cnm.deepdive.mealornomeal.model.Ingredient;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The type Ingredient repository.
 */
public class IngredientRepository {

  private static final String AUTH_HEADER_FORMAT = "Bearer %s";
  private static final int NETWORK_POOL_SIZE = 4;

  private final BackEndService backEndService;
  private final ExecutorService networkPool;

  /**
   * Instantiates a new Ingredient repository.
   */
  public IngredientRepository() {
    backEndService = BackEndService.getInstance();
    networkPool = Executors.newFixedThreadPool(NETWORK_POOL_SIZE);
  }

  /**
   * Gets instance.
   *
   * @return the instance
   */
  public static IngredientRepository getInstance() {
    return InstanceHolder.INSTANCE;
  }

  /**
   * Gets all.
   *
   * @param idToken the id token
   * @return the all
   */
  public Single<List<Ingredient>> getAll(String idToken) {
    return backEndService.getAllIngredients(getHeader(idToken))
        .subscribeOn(Schedulers.from(networkPool));
  }

  /**
   * Save single.
   *
   * @param ingredient the ingredient
   * @param idToken    the id token
   * @return the single
   */
  public Single<Ingredient> save(Ingredient ingredient, String idToken) {
    return backEndService.postIngredient(getHeader(idToken), ingredient)
        .subscribeOn(Schedulers.from(networkPool));
  }

  /**
   * Delete completable.
   *
   * @param ingredient the ingredient
   * @param idToken    the id token
   * @return the completable
   */
  public Completable delete(Ingredient ingredient, String idToken) {
    return backEndService.delete(getHeader(idToken), ingredient.getId())
        .subscribeOn(Schedulers.from(networkPool));
  }

  private static class InstanceHolder {

    private static final IngredientRepository INSTANCE = new IngredientRepository();

  }

  private String getHeader(String idToken) {
    return String.format(AUTH_HEADER_FORMAT, idToken);
  }

}