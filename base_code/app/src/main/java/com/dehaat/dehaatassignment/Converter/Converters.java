package com.dehaat.dehaatassignment.Converter;

import com.dehaat.dehaatassignment.model.books;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.room.TypeConverter;

public class Converters {



    @TypeConverter
    public static List<books> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<books>>() {}.getType();
        Gson gson = new Gson();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(List<books> someObjects) {
        Gson gson = new Gson();
        return gson.toJson(someObjects);
    }
}
