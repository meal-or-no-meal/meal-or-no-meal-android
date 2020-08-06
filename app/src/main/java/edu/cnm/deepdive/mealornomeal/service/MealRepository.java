/*
 * <!--
 *   Copyright 2020 Meal or no Meal
 *  Paul Cutter, Mickie Morlang, Ambar Rodriguez, Levi Sanchez
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0>
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * -->
 */

package edu.cnm.deepdive.mealornomeal.service;

import edu.cnm.deepdive.mealornomeal.model.Meal;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This Meal repository is the point of connection between front end and back end.
 */
public class MealRepository {

  private static final String AUTH_HEADER_FORMAT = "Bearer %s";
  private static final int NETWORK_POOL_SIZE = 4;

  private final BackEndService backEndService;
  private final ExecutorService networkPool;

  /**
   * Instantiates a new Meal repository.
   */
  public MealRepository() {
    backEndService = BackEndService.getInstance();
    networkPool = Executors.newFixedThreadPool(NETWORK_POOL_SIZE);
  }

  /**
   * Gets instance.
   *
   * @return the instance
   */
  public static MealRepository getInstance() {
    return InstanceHolder.INSTANCE;
  }

  /**
   * Gets all meals.
   *
   * @param idToken the id token
   * @return the all meals
   */
  public Single<List<Meal>> getAllMeals(String idToken) {
    return backEndService.getAllMeals(getHeader(idToken))
        .subscribeOn(Schedulers.from(networkPool));
  }

  /**
   * Gets meal.
   *
   * @param idToken the id token
   * @param id      the id
   * @return the meal
   */
  public Single<Meal> getMeal(String idToken, Long id) {
    return backEndService.get(getHeader(idToken), id)
        .subscribeOn(Schedulers.from(networkPool));
  }

  /**
   * Saves a single meal.
   *
   * @param idToken the id token
   * @param meal    the meal
   * @return the single
   */
  public Single<Meal> save(String idToken, Meal meal) {
    if (meal.getId() == null) {
      return backEndService.postMeal(getHeader(idToken), meal)
          .subscribeOn(Schedulers.from(networkPool));
    } else {
      return backEndService.putMeal(getHeader(idToken), meal, meal.getId())
          .subscribeOn(Schedulers.from(networkPool));
    }
  }

  /**
   * Deletes completable a single meal.
   *
   * @param meal    the meal
   * @param idToken the id token
   * @return the completable
   */
  public Completable delete(Meal meal, String idToken) {
    if (meal.getId() == null) {
      return Completable.fromAction(() -> {
      })
          .subscribeOn(Schedulers.io());
    } else {
      return backEndService.delete(getHeader(idToken), meal.getId())
          .subscribeOn(Schedulers.from(networkPool));
    }
  }

  private String getHeader(String idToken) {
    return String.format(AUTH_HEADER_FORMAT, idToken);
  }

  /**
   * creates a new instance and holder.
   */
  private static class InstanceHolder {

    private static final MealRepository INSTANCE = new MealRepository();

  }

}
