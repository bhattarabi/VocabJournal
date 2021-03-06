package com.abhiyaan.androidapp.vocabjournal.db;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by Binaya Bhattarai on 3/26/2018.
 */
public class DateConverter {

    @TypeConverter
    public static Date toDate(Long timestamp){
        return timestamp == null? null: new Date(timestamp);
    }

    @TypeConverter
    public static Long toLong(Date date){
        return date == null? null: date.getTime();
    }
}
