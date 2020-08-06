package edu.cnm.deepdive.mealornomeal.service;

import android.content.Context;
import androidx.core.widget.AutoScrollHelper;
import edu.cnm.deepdive.mealornomeal.model.Calendar;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CalendarRepository {

  private static final int NETWORK_POOL_SIZE = 4;
  private static final String AUTH_HEADER_FORMAT = "Bearer %s";

  private final Context context;
  private final BackEndService backEndService;
  private final ExecutorService networkPool;


  public CalendarRepository(Context context) {
    this.context = context;
    backEndService = BackEndService.getInstance();
    networkPool = Executors.newFixedThreadPool(NETWORK_POOL_SIZE);
  }

  public Single<List<Calendar>> get(String idToken) {
    return backEndService.getCalendars(getHeader(idToken))
        .subscribeOn(Schedulers.from(networkPool));
  }

  public Single<Calendar> get(String idToken, long id) {
    return backEndService.getCalendar(getHeader(idToken), id)
        .subscribeOn(Schedulers.from(networkPool));
  }

  public Single<Calendar> save(String idToken, Calendar calendar) {
    return backEndService.postCalendar(getHeader(idToken), calendar)
        .subscribeOn(Schedulers.from(networkPool));
  }

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
