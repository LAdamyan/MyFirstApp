package com.example.myfirstapp.room;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class StringConverter {


    @TypeConverter
    public static List<String> fromString(String value) {

        Type listType = new TypeToken<List<String>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String toList(List<String> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }


}