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

import android.content.Context;
import androidx.core.widget.AutoScrollHelper;
import edu.cnm.deepdive.mealornomeal.model.Calendar;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This Calendar repository is the point of connection between front end and back end.
 */
public class CalendarRepository {

  private static final int NETWORK_POOL_SIZE = 4;
  private static final String AUTH_HEADER_FORMAT = "Bearer %s";

  private final Context context;
  private final BackEndService backEndService;
  private final ExecutorService networkPool;


  /**
   * Instantiates a new Calendar repository.
   *
   * @param context the context
   */
  public CalendarRepository(Context context) {
    this.context = context;
    backEndService = BackEndService.getInstance();
    networkPool = Executors.newFixedThreadPool(NETWORK_POOL_SIZE);
  }

  /**
   * Get single.
   *
   * @param idToken the id token
   * @return the single
   */
  public Single<List<Calendar>> get(String idToken) {
    return backEndService.getCalendars(getHeader(idToken))
        .subscribeOn(Schedulers.from(networkPool));
  }

  public Single<List<Calendar>> get(String idToken, LocalDate date) {
    return backEndService.getCalendarsForDay(getHeader(idToken), date)
        .subscribeOn(Schedulers.from(networkPool));
  }

  public Single<List<Calendar>> get(String idToken, LocalDate from, LocalDate to) {
    return backEndService.getCalendarsForDateRange(getHeader(idToken), from, to)
        .subscribeOn(Schedulers.from(networkPool));
  }

  /**
   * Get single.
   *
   * @param idToken the id token
   * @param id      the id
   * @return the single
   */
  public Single<Calendar> get(String idToken, long id) {
    return backEndService.getCalendar(getHeader(idToken), id)
        .subscribeOn(Schedulers.from(networkPool));
  }

  /**
   * Save single.
   *
   * @param idToken  the id token
   * @param calendar the calendar
   * @return the single
   */
  public Single<Calendar> save(String idToken, Calendar calendar) {
    return backEndService.postCalendar(getHeader(idToken), calendar)
        .subscribeOn(Schedulers.from(networkPool));
  }

  /**
   * Delete completable.
   *
   * @param idToken  the id token
   * @param calendar the calendar
   * @return the completable
   */
  public Completable delete(String idToken, Calendar calendar) {
    Completable task = (calendar.getId() != null)
        ? backEndService.deleteCalendar(getHeader(idToken), calendar.getId())
        : Completable.complete();
    return task.subscribeOn(Schedulers.from(networkPool));
  }

  private String getHeader(String idToken) {
    return String.format(AUTH_HEADER_FORMAT, idToken);
  }

}
