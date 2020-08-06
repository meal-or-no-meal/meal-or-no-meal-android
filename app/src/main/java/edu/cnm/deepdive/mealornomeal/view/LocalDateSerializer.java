package edu.cnm.deepdive.mealornomeal.view;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateSerializer
    implements JsonDeserializer<LocalDate>, JsonSerializer<LocalDate> {

  private final DateTimeFormatter dateFormat =  DateTimeFormatter.ISO_DATE;

  @Override
  public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    return (LocalDate) dateFormat.parse(json.getAsString());
  }

  @Override
  public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
    return new JsonPrimitive(dateFormat.format(src));
  }
}
