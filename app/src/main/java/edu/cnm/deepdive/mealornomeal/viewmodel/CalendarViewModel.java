package edu.cnm.deepdive.mealornomeal.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import edu.cnm.deepdive.mealornomeal.model.Calendar;
import edu.cnm.deepdive.mealornomeal.service.CalendarRepository;
import edu.cnm.deepdive.mealornomeal.service.GoogleSignInService;
import edu.cnm.deepdive.mealornomeal.viewmodel.CreatedMealsViewModel.AuthenticatedTask;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public class CalendarViewModel extends AndroidViewModel {

  private final CalendarRepository calendarRepository;
  private final GoogleSignInService signInService;
  private final CompositeDisposable pending;
  private final MutableLiveData<Throwable> throwable;
  private final MutableLiveData<List<Calendar>> calendars;
  private final MutableLiveData<Calendar> calendar;
  private final Map<Long, Calendar> calendarMap;

  public CalendarViewModel(
      @NonNull Application application) {
    super(application);
    calendarRepository = new CalendarRepository(application);
    signInService = GoogleSignInService.getInstance();
    pending = new CompositeDisposable();
    throwable = new MutableLiveData<>();
    calendars = new MutableLiveData<>();
    calendar = new MutableLiveData<>();
    calendarMap = new HashMap<>();
    //TODO Preload calendars
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  public LiveData<List<Calendar>> getCalendars() {
    return calendars;
  }

  public LiveData<Calendar> getCalendar() {
    return calendar;
  }

  public void loadCalendars() {
    refreshAndExecute((account) -> calendarRepository.get(account.getIdToken())
        .subscribe(
            calendars::postValue,
            throwable::postValue
        )
    );
  }

  public void save(Calendar calendar) {
    refreshAndExecute((account) -> calendarRepository.save(account.getIdToken(), calendar)
        .subscribe(
            (cal) -> {
              calendarMap.put(cal.getId(), cal);
              List<Calendar> calendars = this.calendars.getValue();
              calendars.add(cal); //TODO are calendars sorted?
              this.calendars.postValue(calendars);
            },
            throwable::postValue
        )
    );
  }

  public void delete(Calendar calendar) {
    refreshAndExecute((account) -> calendarRepository.delete(account.getIdToken(), calendar)
        .subscribe(
            () -> {
              List<Calendar> calendars = this.calendars.getValue();
              if (calendars.remove(calendar)) {
                calendarMap.remove(calendar.getId());
                this.calendars.postValue(calendars);
              }
            },
            throwable::postValue
        )
    );
  }

  private void refreshAndExecute(CreatedMealsViewModel.AuthenticatedTask task) {
    throwable.setValue(null);
    signInService.refresh()
        .addOnSuccessListener((account) -> pending.add(task.execute(account)))
        .addOnFailureListener(throwable::postValue);
  }

  @FunctionalInterface
  public interface AuthenticatedTask {

    Disposable execute(GoogleSignInAccount account);
  }


}
