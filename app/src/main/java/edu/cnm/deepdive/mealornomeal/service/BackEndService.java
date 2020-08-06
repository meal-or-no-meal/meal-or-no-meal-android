package edu.cnm.deepdive.mealornomeal.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cnm.deepdive.mealornomeal.BuildConfig;
import edu.cnm.deepdive.mealornomeal.model.Calendar;
import edu.cnm.deepdive.mealornomeal.model.Ingredient;
import edu.cnm.deepdive.mealornomeal.model.Meal;
import edu.cnm.deepdive.mealornomeal.view.LocalDateSerializer;
import io.reactivex.Completable;
import io.reactivex.Single;
import java.time.LocalDate;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BackEndService {

  @GET("meals")
  Single<List<Meal>> getAllMeals(@Header("Authorization") String authHeader);

  @GET("meals/{id}")
  Single<Meal> get(@Header("Authorization") String authHeader, @Path("id") Long id);

  @POST("meals")
  Single<Meal> postMeal(@Header("Authorization") String authHeader, @Body Meal meal);

  @PUT("meals/{id}")
  Single<Meal> putMeal(@Header("Authorization") String authHeader, @Body Meal meal, @Path("id") Long id);

  @DELETE("meals/{id}")
  Completable delete(@Header("Authorization") String authHeader, @Path("id") Long id);

  @GET("ingredients/{id}")
  Single<Ingredient> getIngredient(@Header("Authorization") String authHeader, @Path("id") long id);

  @GET("ingredients")
  Single<List<Ingredient>> getAllIngredients(@Header("Authorization") String authHeader);

  @POST("ingredients")
  Single<Ingredient> postIngredient(@Header("Authorization") String authHeader, @Body Ingredient ingredient);

  @GET("calendars")
  Single<List<Calendar>> getCalendars(@Header("Authorization") String authHeader);

  @GET("calendars/{id}")
  Single<Calendar> getCalendar(@Header("Authorization") String authHeader, @Path("id") long id);

  @POST("calendars")
  Single<Calendar> postCalendar(@Header("Authorization") String authHeader, @Body Calendar calendar);

  @DELETE("calendars/{id}")
  Completable deleteCalendar(@Header("Authorization") String authHeader, @Path("id") long id);




  static BackEndService getInstance() {
    return InstanceHolder.INSTANCE;
  }


  class InstanceHolder {

    private static final BackEndService INSTANCE;

    static {
      Gson gson = new GsonBuilder()
          .excludeFieldsWithoutExposeAnnotation()
          .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
          .create();
      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
      interceptor.setLevel(BuildConfig.DEBUG ? Level.BODY : Level.NONE);
      OkHttpClient client = new OkHttpClient.Builder()
          .addInterceptor(interceptor)
          .build();
      Retrofit retrofit = new Retrofit.Builder()
          .addConverterFactory(GsonConverterFactory.create(gson))
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .client(client)
          .baseUrl(BuildConfig.BASE_URL)
          .build();
      INSTANCE = retrofit.create(BackEndService.class);

    }
  }

}
