package edu.cnm.deepdive.mealornomeal.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cnm.deepdive.mealornomeal.BuildConfig;
import edu.cnm.deepdive.mealornomeal.model.Ingredient;
import edu.cnm.deepdive.mealornomeal.model.Meal;
import io.reactivex.Completable;
import io.reactivex.Single;
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

/**
 * The interface Back end service.
 */
public interface BackEndService {

  /**
   * Gets all meals.
   *
   * @param authHeader the auth header
   * @return the all meals
   */
  @GET("meals")
  Single<List<Meal>> getAllMeals(@Header("Authorization") String authHeader);

  /**
   * Get single.
   *
   * @param authHeader the auth header
   * @return the single
   */
  @GET("meals/{id}")
  Single<Meal> get(@Header("Authorization") String authHeader);

  /**
   * Post meal single.
   *
   * @param authHeader the auth header
   * @param meal       the meal
   * @return the single
   */
  @POST("meals")
  Single<Meal> postMeal(@Header("Authorization") String authHeader, @Body Meal meal);

  /**
   * Put meal single.
   *
   * @param authHeader the auth header
   * @param meal       the meal
   * @param id         the id
   * @return the single
   */
  @PUT("meals/{id}")
  Single<Meal> putMeal(@Header("Authorization") String authHeader, @Body Meal meal, @Path("id") Long id);

  /**
   * Delete completable.
   *
   * @param authHeader the auth header
   * @param id         the id
   * @return the completable
   */
  @DELETE("meals/{id}")
  Completable delete(@Header("Authorization") String authHeader, @Path("id") Long id);

  /**
   * Gets ingredient.
   *
   * @param authHeader the auth header
   * @return the ingredient
   */
  @GET("ingredients/{id}")
  Single<Ingredient> getIngredient(@Header("Authorization") String authHeader);

  /**
   * Gets all ingredients.
   *
   * @param authHeader the auth header
   * @return the all ingredients
   */
  @GET("ingredients")
  Single<List<Ingredient>> getAllIngredients(@Header("Authorization") String authHeader);

  /**
   * Post ingredient single.
   *
   * @param authHeader the auth header
   * @param ingredient the ingredient
   * @return the single
   */
  @POST("ingredients")
  Single<Ingredient> postIngredient(@Header("Authorization") String authHeader, @Body Ingredient ingredient);

  //TODO Add methods for Calendar


  /**
   * Gets instance.
   *
   * @return the instance
   */
  static BackEndService getInstance() {
    return InstanceHolder.INSTANCE;
  }


  /**
   * The type Instance holder.
   */
  class InstanceHolder {

    private static final BackEndService INSTANCE;

    static {
      Gson gson = new GsonBuilder()
          .excludeFieldsWithoutExposeAnnotation()
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
